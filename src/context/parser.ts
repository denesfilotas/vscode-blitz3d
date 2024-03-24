import { readFileSync } from 'fs';
import { isAbsolute, join } from 'path';
import * as vscode from 'vscode';
import { removeType } from '../util/functions';
import { BlitzToker } from '../util/toker';
import * as bb from './types';
import { obtainWorkingDir } from './context';

function isTerm(c: string | undefined): boolean {
    return !c || c == ':' || c == '\n';
}

export interface Parser {
    parse(intext: string, uri: vscode.Uri): bb.ParseResult;
    getResults(): bb.ParseResult;
}

export class BlitzParser implements Parser {

    uri: vscode.Uri;

    bbdoc: {
        descLines: string[],
        paramLines: Map<string, string>,
        authors: string[],
        returns: string,
        deprecated?: string,
        since: string;
    };

    consts: bb.Variable[] = [];
    globals: bb.Variable[] = [];
    structs: bb.Type[] = [];
    funcs: bb.Function[] = [];
    datas: bb.Data[] = [];
    labels: bb.Token[] = [];
    diagnostics: Map<vscode.Uri, vscode.Diagnostic[]> = new Map<vscode.Uri, vscode.Diagnostic[]>();

    arrayDecls: Map<string, bb.DimmedArray> = new Map<string, bb.DimmedArray>();
    included: Set<string> = new Set<string>();

    toker: BlitzToker;

    constructor(intext: string, uri: vscode.Uri) {

        this.toker = new BlitzToker(intext);
        this.uri = uri;
        this.consts = [];
        this.structs = [];
        this.funcs = [];
        this.datas = [];
        this.diagnostics = new Map<vscode.Uri, vscode.Diagnostic[]>();
        this.bbdoc = {
            descLines: [],
            paramLines: new Map<string, string>(),
            authors: [],
            returns: '',
            since: ''
        };
        this.diagnostics.set(uri, []);
        this.parseStmtSeq('prog');

        if (this.toker.curr() != 'eof') this.expecting('<eof>');
    }

    private expecting(thing: string) {
        this.diagnostics.get(this.uri)?.push({
            range: this.toker.range(),
            message: `Syntax error: expecting ${thing}, got ${this.toker.curr().replace('\n', '<eol>')}`,
            severity: vscode.DiagnosticSeverity.Error
        });
    }

    private exception(message: string) {
        this.diagnostics.get(this.uri)?.push({
            range: this.toker.range(),
            message: 'Syntax error: ' + message,
            severity: vscode.DiagnosticSeverity.Error
        });
    }

    getResults(): bb.ParseResult {
        return this;
    }

    parse(intext: string, uri: vscode.Uri): bb.ParseResult {
        this.toker = new BlitzToker(intext);
        this.uri = uri;

        this.consts = this.consts.filter(constant => constant.uri.path != uri.path);
        this.diagnostics.set(uri, []);
        this.funcs = this.funcs.filter(fun => fun.uri.path != uri.path);
        this.globals = this.globals.filter(global => global.uri.path != uri.path);
        this.labels = this.labels.filter(label => label.uri.path != uri.path);
        this.structs = this.structs.filter(type => type.uri.path != uri.path);

        this.parseStmtSeq('prog');
        if (this.toker.curr() != 'eof') this.expecting('<eof>');

        return this;
    }

    parseIdent(): bb.Ident {
        if (this.toker.curr() != 'ident') {
            this.expecting('identifier');
            return { ident: '<unnamed>', name: '<unnamed>' };
        }
        const ident = this.toker.text().toLowerCase();
        const name = this.toker.text();
        this.toker.next();
        return { ident, name };
    }

    parseChar(c: string) {
        if (this.toker.curr() != c) this.expecting(`'${c}'`);
        this.toker.next();
    }

    parseStmtSeq(scope: 'prog' | 'block' | 'fun' | 'line') {
        for (; ;) {
            while (this.toker.curr() == ':' || (scope != 'line' && this.toker.curr() == '\n')) {
                const bbdoc = this.toker.text();
                if (bbdoc.startsWith(';; ')) {
                    this.bbdoc.descLines.push(bbdoc.substring(3));
                } else if (bbdoc.startsWith(';;param ')) {
                    const line = bbdoc.substring(8);
                    if (line.includes(' ')) {
                        const param = line.substring(0, line.indexOf(' '));
                        const desc = line.substring(line.indexOf(' ') + 1);
                        this.bbdoc.paramLines.set(param, desc);
                    } else {
                        this.diagnostics.get(this.uri)?.push({
                            message: `BBDoc: incomplete parameter description`,
                            range: this.toker.range(),
                            severity: vscode.DiagnosticSeverity.Warning
                        });
                    }
                } else if (bbdoc.startsWith(';;author ')) {
                    this.bbdoc.authors.push(bbdoc.substring(9));
                } else if (bbdoc.startsWith(';;return ')) {
                    this.bbdoc.returns = bbdoc.substring(9);
                } else if (bbdoc.startsWith(';;since ')) {
                    this.bbdoc.since = bbdoc.substring(8);
                } else if (bbdoc.startsWith(';;deprecated')) {
                    this.bbdoc.deprecated = bbdoc.substring(12).trim();
                } else if (bbdoc.startsWith(';;')) {
                    this.diagnostics.get(this.uri)?.push({
                        message: `Invalid BBDoc`,
                        range: this.toker.range(),
                        severity: vscode.DiagnosticSeverity.Warning
                    });
                }
                this.toker.next();
            }
            let pos = this.toker.range();
            switch (this.toker.curr()) {
                case 'include':
                    if (this.toker.next() != 'stringconst') this.expecting('include filename');
                    const inc = this.toker.text();
                    const start = this.toker.range().start;
                    this.toker.next();
                    const infile = inc.substring(1, inc.length - 1).toLowerCase();
                    const dir = obtainWorkingDir(this.uri);
                    const infilepath = isAbsolute(infile) ? infile : join(dir, infile);
                    if (this.included.has(infilepath)) break;
                    let intext = '';
                    try {
                        intext = readFileSync(infilepath).toString();
                        const t_toker = this.toker;
                        this.toker = new BlitzToker(intext);
                        const t_uri = this.uri;
                        this.uri = vscode.Uri.file(infilepath);
                        this.diagnostics.set(this.uri, []);
                        this.included.add(infilepath);
                        this.parseStmtSeq(scope);
                        if (this.toker.curr() != 'eof') this.expecting('<eof>');
                        this.toker = t_toker;
                        this.uri = t_uri;
                    } catch (err) {
                        this.diagnostics.get(this.uri)?.push({
                            message: 'Unable to open include file ' + infile,
                            range: new vscode.Range(start, this.toker.range().start),
                            severity: vscode.DiagnosticSeverity.Error
                        });
                    }
                    break;
                case 'ident':
                    {
                        const ident = this.parseIdent();
                        const tag = this.parseTypeTag();
                        if (!this.arrayDecls.has(ident.ident) && this.toker.curr() != '=' && this.toker.curr() != '\\' && this.toker.curr() != '[') {
                            let exprs;
                            if (this.toker.curr() == '(') {
                                let nest = 1, k;
                                for (k = 1; ; ++k) {
                                    const c = this.toker.lookAhead(k);
                                    if (isTerm(c)) {
                                        this.exception('mismatched brackets');
                                        break;
                                    } else if (c == '(') ++nest;
                                    else if (c == ')' && --nest == 0) break;
                                }
                                if (isTerm(this.toker.lookAhead(++k))) {
                                    this.toker.next();
                                    exprs = this.parseExprSeq();
                                    if (this.toker.curr() != ')') this.expecting("')'");
                                    this.toker.next();
                                } else {
                                    exprs = this.parseExprSeq();
                                }
                            } else {
                                exprs = this.parseExprSeq();
                            }
                        } else {
                            // const variable = this.parseVar(ident, tag);
                            this.parseVar(ident, tag);
                            // if (!this.arrayDecls.has(ident.ident) && !context.concat(this.globals, this.consts).find(local => local.ident == variable.ident)) context.push(variable);
                            if (this.toker.curr() != '=') this.expecting('variable assignment');
                            this.toker.next();
                            this.parseExpr(false);
                            // variable.range = new vscode.Range(variable.range.start, this.toker.range().end);
                        }
                    }
                    break;
                case 'if':
                    {
                        this.toker.next();
                        this.parseIf(scope == 'fun' ? 'fun' : 'block');
                        if (this.toker.curr() == 'endif') this.toker.next();
                    }
                    break;
                case 'while':
                    {
                        this.toker.next();
                        this.parseExpr(false);
                        this.parseStmtSeq(scope == 'fun' ? 'fun' : 'block');
                        if (this.toker.curr() == 'wend') this.toker.next();
                        else this.expecting("'Wend'");
                    }
                    break;
                case 'repeat':
                    {
                        this.toker.next();
                        this.parseStmtSeq(scope == 'fun' ? 'fun' : 'block');
                        const curr = this.toker.curr();
                        if (curr != 'until' && curr != 'forever') this.expecting("'Until' or 'Forever'");
                        this.toker.next();
                        if (curr == 'until') this.parseExpr(false);
                    }
                    break;
                case 'select':
                    {
                        this.toker.next();
                        this.parseExpr(false);
                        for (; ;) {
                            while (isTerm(this.toker.curr())) this.toker.next();
                            if (this.toker.curr() == 'case') {
                                this.toker.next();
                                const expressions = this.parseExprSeq();
                                if (expressions.length == 0) this.expecting('expression sequence');
                                this.parseStmtSeq(scope == 'fun' ? 'fun' : 'block');
                                continue;
                            } else if (this.toker.curr() == 'default') {
                                this.toker.next();
                                this.parseStmtSeq(scope == 'fun' ? 'fun' : 'block');
                                if (this.toker.curr() != 'endselect') this.expecting("'End Select'");
                                break;
                            } else if (this.toker.curr() == 'endselect') {
                                break;
                            }
                            this.expecting("'Case', 'Default' or 'End Select'");
                            break;
                        }
                        this.toker.next();
                    }
                    break;
                case 'for':
                    {
                        let variable: bb.Variable;
                        this.toker.next();
                        variable = this.parseVar();
                        // if (!context.concat(this.globals, this.consts).find(local => local.ident == variable.ident)) context.push(variable);
                        if (this.toker.curr() != '=') this.expecting('variable assignment');
                        if (this.toker.next() == 'each') {
                            this.toker.next();
                            this.parseIdent();
                            this.parseStmtSeq(scope == 'fun' ? 'fun' : 'block');
                            if (this.toker.curr() != 'next') this.expecting("'Next'");
                            this.toker.next();
                        } else {
                            let from, to, step;
                            from = this.parseExpr(false);
                            if (this.toker.curr() != 'to') this.expecting("'To'");
                            this.toker.next();
                            to = this.parseExpr(false);
                            if (this.toker.curr() == 'step') {
                                this.toker.next();
                                step = this.parseExpr(false);
                            } else step = {
                                kind: 'const',
                                tag: '%',
                                value: 1
                            };
                            this.parseStmtSeq(scope == 'fun' ? 'fun' : 'block');
                            if (this.toker.curr() != 'next') this.expecting("'Next'");
                            this.toker.next();
                        }
                    }
                    break;
                case 'exit':
                    this.toker.next();
                    break;
                case 'goto':
                case 'gosub':
                    {
                        this.toker.next();
                        this.parseIdent();
                    }
                    break;
                case 'return':
                    {
                        if (scope != 'fun' && scope != 'line') this.diagnostics.get(this.uri)?.push({
                            message: 'Cannot return from outside of a function',
                            range: this.toker.range(),
                            severity: vscode.DiagnosticSeverity.Error
                        });
                        this.toker.next();
                        this.parseExpr(true);
                    }
                    break;
                case 'delete':
                    {
                        if (this.toker.next() == 'each') {
                            this.toker.next();
                            this.parseIdent();
                        } else {
                            this.parseExpr(false);
                        }
                    }
                    break;
                case 'insert':
                    {
                        this.toker.next();
                        this.parseExpr(false);
                        if (this.toker.curr() != 'before' && this.toker.curr() != 'after') this.expecting("'Before' or 'After'");
                        this.toker.next();
                        this.parseExpr(false);
                    }
                    break;
                case 'read':
                    do {
                        this.toker.next();
                        this.parseVar();
                        pos = this.toker.range();
                    } while (this.toker.curr() == ',');
                    break;
                case 'restore':
                    if (this.toker.next() == 'ident') {

                    }
                    this.toker.next();
                    break;
                case 'data':
                    if (scope != 'prog') this.exception('Data can only be declared in the main program');
                    do {
                        this.toker.next();
                        this.datas.push({
                            kind: 'data', // TODO
                            expression: this.parseExpr(false)
                        });
                    } while (this.toker.curr() == ',');
                    break;
                case 'type':
                    if (scope != 'prog') this.exception('Types can only be declared in the main program');
                    this.structs.push(this.parseStructDecl());
                    break;
                case 'const':
                    if (scope != 'prog') this.exception('Constants can only be declared in the main program');
                    do {
                        const start = this.toker.range().start;
                        this.toker.next();
                        this.consts.push(this.parseVarDecl('global', true, start));
                    } while (this.toker.curr() == ',');
                    break;
                case 'function':
                    if (scope != 'prog') this.exception('Functions can only be defined in the main program');
                    const fun = this.parseFuncDecl();
                    fun.description = this.bbdoc.descLines.join('  \n');
                    for (const [paramName, paramDesc] of this.bbdoc.paramLines) {
                        const param = fun.params.find(param => param.ident == removeType(paramName).toLowerCase());
                        if (!param) {
                            this.diagnostics.get(this.uri)?.push({
                                message: `BBDoc for non-existent parameter '${paramName}'`,
                                range: fun.declarationRange,
                                severity: vscode.DiagnosticSeverity.Warning
                            });
                            continue;
                        }
                        param.description = paramDesc;
                    }
                    fun.authors = this.bbdoc.authors;
                    fun.returns = this.bbdoc.returns;
                    fun.since = this.bbdoc.since;
                    fun.deprecated = this.bbdoc.deprecated;
                    this.funcs.push(fun);
                    this.bbdoc = {
                        descLines: [],
                        paramLines: new Map<string, string>(),
                        authors: [],
                        returns: '',
                        deprecated: undefined,
                        since: ''
                    };
                    break;
                case 'dim':
                    do {
                        this.toker.next();
                        this.parseArrayDecl();
                    } while (this.toker.curr() == ',');
                    break;
                case 'local':
                    do {
                        const start = this.toker.range().start;
                        this.toker.next();
                        // context.push(this.parseVarDecl('local', false, start));
                        this.parseVarDecl('local', false, start);
                    } while (this.toker.curr() == ',');
                    break;
                case 'global':
                    if (scope != 'prog') this.exception('global variables can only be declared in the main program');
                    do {
                        const start = this.toker.range().start;
                        this.toker.next();
                        this.globals.push(this.parseVarDecl('global', false, start));
                    } while (this.toker.curr() == ',');
                    break;
                case '.':
                    {
                        const start = this.toker.range().start;
                        this.toker.next();
                        const range = this.toker.range();
                        const ident = this.parseIdent();
                        if (this.labels.find(label => label.ident == ident.ident)) this.diagnostics.get(this.uri)?.push({
                            message: `Duplicate label '${ident.name}'`,
                            range: pos,
                            severity: vscode.DiagnosticSeverity.Error
                        });
                        this.labels.push({
                            kind: 'label',
                            ...ident,
                            range: new vscode.Range(start, this.toker.range().start),
                            declarationRange: range,
                            uri: this.uri
                        });
                    }
                    break;
                case 'eof':
                    return;
                default:
                    if (scope != 'prog') return;
                    this.exception(`unexpected token '${this.toker.curr().replace('\n', '<eol>')}'`);
                    this.toker.next();
                    break;
            }
        }
    }

    parseTypeTag(): string {
        switch (this.toker.curr()) {
            case '%':
                this.toker.next();
                return '%';
            case '#':
                this.toker.next();
                return '#';
            case '$':
                this.toker.next();
                return '$';
            case '.':
                this.toker.next();
                return this.parseIdent().ident;
            default:
                return '';
        }
    }

    parseVar(ident?: bb.Ident, tag?: string): bb.Variable {
        if (!ident && !tag) {
            ident = this.parseIdent();
            tag = this.parseTypeTag();
        }
        let variable: bb.Variable;
        if (this.toker.curr() == '(') {
            this.toker.next();
            const exprs = this.parseExprSeq();
            if (this.toker.curr() != ')') this.expecting("')");
            this.toker.next();
            variable = {
                kind: 'array',
                ...ident!,
                tag: tag!,
                constant: false,
                range: this.toker.range(), // overriden later
                declarationRange: this.toker.range(),
                uri: this.uri,
            };
        } else {
            variable = {
                kind: 'variable',
                ...ident!,
                tag: tag!,
                constant: false,
                range: this.toker.range(), // overriden later
                declarationRange: this.toker.range(),
                uri: this.uri
            };
        }

        for (; ;) {
            if (this.toker.curr() == '\\') {
                this.toker.next();
                this.parseIdent();
                this.parseTypeTag();
            } else if (this.toker.curr() == '[') {
                this.toker.next();
                const exprs = this.parseExprSeq();
                if (exprs.length == 0) this.expecting('expression');
                else if (exprs.length > 1 || this.toker.curr() != ']') this.expecting("']'");
                this.toker.next();
            } else {
                break;
            }
        }
        return variable;
    }

    parseVarDecl(kind: 'local' | 'global' | 'param' | 'field', constant: boolean, start: vscode.Position): bb.Variable {
        const range = this.toker.range();
        const ident = this.parseIdent();
        const tag = this.parseTypeTag();
        if (this.toker.curr() == '[') {
            if (constant) this.exception('Blitz arrays cannot be constant');
            this.toker.next();
            const exprs = this.parseExprSeq();
            if (exprs.length == 0) this.expecting('expression');
            else if (exprs.length > 1 || this.toker.curr() != ']') this.expecting("']'");
            this.toker.next();
            return {
                kind: 'vector',
                tag: tag,
                range: range,
                declarationRange: range,
                uri: this.uri,
                constant: constant,
                ...ident
            };
        } else {
            let expr;
            if (this.toker.curr() == '=') {
                this.toker.next();
                expr = this.parseExpr(false);
            } else if (constant) {
                this.exception('Constants must be initialized');
            }
            return {
                kind: kind,
                tag: tag,
                range: new vscode.Range(start, this.toker.range().end),
                declarationRange: range,
                constant: constant,
                ...ident,
                uri: this.uri,
                value: expr?.value
            };
        }
    }

    parseArrayDecl(): bb.DimmedArray | undefined {
        const range = this.toker.range();
        const ident = this.parseIdent();
        if (!ident) return;
        const tag = this.parseTypeTag();
        if (this.toker.curr() != '(') this.expecting("'('");
        this.toker.next();
        const expressions = this.parseExprSeq();
        if (this.toker.curr() != ')') this.expecting("')'");
        if (expressions.length == 0) this.exception('arrays must have at least one dimension');
        this.toker.next();
        const d = {
            kind: 'array',
            ...ident,
            tag: tag,
            dimension: expressions.length,
            range: range,
            declarationRange: range,
            uri: this.uri,
            constant: false
        };
        this.arrayDecls.set(ident.ident, d);
        return d;
    }

    parseFuncDecl(): bb.Function {
        const declRangeStart = this.toker.range().start;
        this.toker.next();
        const range = this.toker.range();
        const ident = this.parseIdent();
        const tag = this.parseTypeTag();
        if (this.toker.curr() != '(') this.expecting("'('");
        const params = [];
        if (this.toker.next() != ')') {
            for (; ;) {
                params.push(this.parseVarDecl('param', false, this.toker.range().start));
                if (this.toker.curr() != ',') break;
                this.toker.next();
            }
            if (this.toker.curr() != ')') this.expecting("')'");
        }
        this.toker.next();
        const locals = [...params];
        this.parseStmtSeq('fun');
        if (this.toker.curr() != 'endfunction') this.expecting("'End Function'");
        const declRangeEnd = this.toker.range().end;
        this.toker.next();
        return {
            kind: 'function',
            ...ident,
            tag: tag,
            params: params,
            locals: locals,
            range: new vscode.Range(declRangeStart, declRangeEnd),
            declarationRange: range,
            uri: this.uri
        };
    }

    parseStructDecl(): bb.Type {
        const pos = this.toker.range().start;
        this.toker.next();
        const range = this.toker.range();
        const ident = this.parseIdent();
        while (this.toker.curr() == '\n') this.toker.next();
        const fields: bb.Variable[] = [];
        while (this.toker.curr() == 'field') {
            const start = this.toker.range().start;
            const lineFields = [];
            do {
                this.toker.next();
                const field = this.parseVarDecl('field', false, start);
                lineFields.push(field);
            } while (this.toker.curr() == ',');
            if (this.toker.text().startsWith(';')) {
                for (const field of lineFields) field.description = this.toker.text().substring(1).trim();
            }
            fields.push(...lineFields);
            while (this.toker.curr() == '\n') this.toker.next();
        }
        if (this.toker.curr() != 'endtype') this.expecting("'Field' or 'End Type'");
        this.toker.next();
        if (this.bbdoc.paramLines.size + this.bbdoc.returns.length > 0) this.diagnostics.get(this.uri)?.push({
            message: 'Unsupported BBDoc keyword used for this type',
            range: range,
            severity: vscode.DiagnosticSeverity.Warning
        });
        const type = {
            kind: 'type',
            ...ident,
            fields: fields,
            range: new vscode.Range(pos, this.toker.range().start),
            declarationRange: range,
            uri: this.uri,
            description: this.bbdoc.descLines.join('  \n'),
            authors: this.bbdoc.authors,
            since: this.bbdoc.since
        };
        this.bbdoc = {
            descLines: [],
            paramLines: new Map<string, string>(),
            authors: [],
            returns: '',
            since: ''
        };
        return type;
    }

    parseIf(scope: 'block' | 'fun'): any {
        let expr, stmts, elseOpt;
        expr = this.parseExpr(false);
        if (this.toker.curr() == 'then') this.toker.next();
        const blockIf = isTerm(this.toker.curr());
        stmts = this.parseStmtSeq(blockIf ? scope == 'fun' ? 'fun' : 'block' : 'line');

        if (this.toker.curr() == 'elseif') {
            // const range = this.toker.range();
            this.toker.next();
            const ifNode = this.parseIf(scope);
            // ifNode.range.start = range.start;
            elseOpt = [];
            elseOpt.push(ifNode);
        } else if (this.toker.curr() == 'else') {
            this.toker.next();
            elseOpt = this.parseStmtSeq(blockIf ? scope == 'fun' ? 'fun' : 'block' : 'line');
        }
        if (blockIf) {
            if (this.toker.curr() != 'endif') this.expecting("'ElseIf', 'Else If', 'Else', 'EndIf' or 'End If'");
        } else if (this.toker.curr() != '\n') this.expecting('<eol>');

        return {
            kind: 'if',
            expr: expr,
            stmts: stmts,
            elseOpt: elseOpt,
            range: this.toker.range()
        };
    }

    parseExprSeq() {
        const expressions = [];
        let optional = true;
        for (let e; e = this.parseExpr(optional);) {
            expressions.push(e);
            if (this.toker.curr() != ',') break;
            this.toker.next();
            optional = false;
        }
        return expressions;
    }

    parseExpr(optional: boolean): bb.Expression | undefined {
        const start = this.toker.range().start;
        if (this.toker.curr() == 'not') {
            this.toker.next();
            const lhs = this.parseExpr1(false);
            if (!lhs) return;
            const value = lhs.value == undefined ? undefined : lhs.value == 0;
            return {
                kind: '?',
                lhs: lhs,
                value: value ? 1 : 0,
                range: new vscode.Range(start, this.toker.range().start),
                uri: this.uri
            };
        }
        return this.parseExpr1(optional);
    }

    parseExpr1(optional: boolean): bb.Expression | undefined {
        const start = this.toker.range().start;
        let lhs = this.parseExpr2(optional);
        if (!lhs) return;
        for (; ;) {
            const c = this.toker.curr();
            if (c != 'and' && c != 'or' && c != 'xor') return lhs;
            this.toker.next();
            const rhs = this.parseExpr2(false);
            let value: number | undefined;
            if (lhs.value != undefined && rhs && rhs.value != undefined) {
                if (lhs.kind != '%' && lhs.kind != '?') this.diagnostics.get(this.uri)?.push({
                    message: 'This type is not suitable for bitwise operation: ' + lhs.kind.replace('.', lhs.type ?? ''),
                    range: lhs.range,
                    severity: vscode.DiagnosticSeverity.Warning
                });
                if (rhs.kind != '%' && rhs.kind != '?') this.diagnostics.get(this.uri)?.push({
                    message: 'This type is not suitable for bitwise operation: ' + rhs.kind.replace('.', rhs.type ?? ''),
                    range: rhs.range,
                    severity: vscode.DiagnosticSeverity.Warning
                });
                if (lhs.kind != rhs.kind) {
                    this.diagnostics.get(this.uri)?.push({
                        message: `Ambigous type conversion applying bitwise operator '${c}'`,
                        relatedInformation: [
                            {
                                location: {
                                    uri: this.uri,
                                    range: lhs.range
                                },
                                message: 'Left hand side has kind ' + lhs.kind
                            },
                            {
                                location: {
                                    uri: this.uri,
                                    range: rhs.range
                                },
                                message: 'Right hand side has kind ' + rhs.kind
                            }
                        ],
                        range: new vscode.Range(lhs.range.start, rhs.range.end),
                        severity: vscode.DiagnosticSeverity.Warning
                    });
                }
                switch (c) {
                    case 'and':
                        value = lhs.value & rhs.value;
                        break;
                    case 'or':
                        value = lhs.value | rhs.value;
                        break;
                    case 'xor':
                        value = lhs.value ^ rhs.value;
                        break;
                }
            }
            lhs = {
                kind: lhs?.kind == '?' && rhs?.kind == '?' ? '?' : '%',
                lhs: lhs,
                rhs: rhs,
                value: value,
                range: new vscode.Range(start, this.toker.range().start),
                uri: this.uri
            };
        }
    }

    parseExpr2(optional: boolean): bb.Expression | undefined {
        const start = this.toker.range().start;
        let lhs = this.parseExpr3(optional);
        if (!lhs) return;
        for (; ;) {
            const c = this.toker.curr();
            if (c != '<' && c != '>' && c != '=' && c != 'le' && c != 'ge' && c != 'ne') return lhs;
            this.toker.next();
            const rhs = this.parseExpr3(false);
            let value: boolean | undefined;
            if (lhs.value != undefined && rhs && rhs.value != undefined) {
                checkForArithmeticErrors(c, lhs, rhs, this.diagnostics.get(this.uri)!);
                switch (c) {
                    case '<':
                        value = lhs.value < rhs.value;
                        break;
                    case '>':
                        value = lhs.value > rhs.value;
                        break;
                    case '=':
                        value = lhs.value == rhs.value;
                        break;
                    case 'ne':
                        value = lhs.value != rhs.value;
                        break;
                    case 'le':
                        value = lhs.value <= rhs.value;
                        break;
                    case 'ge':
                        value = lhs.value >= rhs.value;
                        break;
                }
            }
            lhs = {
                kind: '?',
                lhs: lhs,
                rhs: rhs,
                range: new vscode.Range(start, this.toker.range().start),
                uri: this.uri
            };
        }
    }

    parseExpr3(optional: boolean) {
        const start = this.toker.range().start;
        let lhs = this.parseExpr4(optional);
        if (!lhs) return;
        for (; ;) {
            const c = this.toker.curr();
            if (c != '+' && c != '-') return lhs;
            this.toker.next();
            const rhs = this.parseExpr4(false);
            let value: any;
            if (lhs.value != undefined && rhs && rhs.value != undefined) {
                checkForArithmeticErrors(c, lhs, rhs, this.diagnostics.get(this.uri)!);
                if (c == '+') {
                    value = lhs.value + rhs.value;
                } else { // -
                    value = lhs.value - rhs.value;
                }
            }
            lhs = {
                kind: '%', // TODO set type correctly
                lhs: lhs,
                rhs: rhs,
                range: new vscode.Range(start, this.toker.range().start),
                uri: this.uri,
                value: value
            };
        }
    }

    parseExpr4(optional: boolean) {
        const start = this.toker.range().start;
        let lhs = this.parseExpr5(optional);
        if (!lhs) return;
        for (; ;) {
            const c = this.toker.curr();
            if (c != 'shl' && c != 'shr' && c != 'sar') return lhs;
            this.toker.next();
            const rhs = this.parseExpr5(false);
            let value: any;
            if (lhs.value != undefined && rhs && rhs.value != undefined) {
                checkForArithmeticErrors(c, lhs, rhs, this.diagnostics.get(this.uri)!);
                switch (c) {
                    case 'shl':
                        value = lhs.value << rhs.value;
                        break;
                    case 'shr':
                        value = lhs.value >> rhs.value;
                        break;
                    case 'sar':
                        value = lhs.value >> rhs.value;
                        break;
                }
            }
            lhs = {
                kind: '%',
                lhs: lhs,
                rhs: rhs,
                range: new vscode.Range(start, this.toker.range().start),
                uri: this.uri,
                value: value
            };
        }
    }

    parseExpr5(optional: boolean) {
        const start = this.toker.range().start;
        let lhs = this.parseExpr6(optional);
        if (!lhs) return;
        for (; ;) {
            const c = this.toker.curr();
            if (c != '*' && c != '/' && c != 'mod') return lhs;
            this.toker.next();
            const rhs = this.parseExpr6(false);
            let value: any;
            if (lhs.value != undefined && rhs && rhs.value != undefined) {
                checkForArithmeticErrors(c, lhs, rhs, this.diagnostics.get(this.uri)!);
                switch (c) {
                    case '*':
                        value = lhs.value * rhs.value;
                        break;
                    case '/':
                        value = lhs.value / rhs.value;
                        break;
                    case 'mod':
                        value = lhs.value % rhs.value;
                        break;
                }
            }
            lhs = {
                kind: '%', // TODO
                lhs: lhs,
                rhs: rhs,
                range: new vscode.Range(start, this.toker.range().start),
                uri: this.uri,
                value: value
            };
        }
    }

    parseExpr6(optional: boolean): bb.Expression | undefined {
        const start = this.toker.range().start;
        let lhs = this.parseUniExpr(optional);
        if (!lhs) return;
        for (; ;) {
            const c = this.toker.curr();
            if (c != '^') return lhs;
            this.toker.next();
            const rhs = this.parseUniExpr(false);
            let value: any;
            if (lhs.value != undefined && rhs && rhs.value != undefined) {
                checkForArithmeticErrors(c, lhs, rhs, this.diagnostics.get(this.uri)!);
                value = Math.pow(lhs.value, rhs.value);
            }
            lhs = {
                kind: '%',
                lhs: lhs,
                rhs: rhs,
                range: new vscode.Range(start, this.toker.range().start),
                uri: this.uri,
                value: value
            };
        }
    }

    parseUniExpr(optional: boolean): bb.Expression | undefined {
        let result: bb.Expression | undefined;
        let t;
        const c = this.toker.curr();
        const start = this.toker.range().start;
        switch (c) {
            case 'int':
                if (this.toker.next() == '%') this.toker.next();
                result = {
                    kind: '%',
                    value: this.parseUniExpr(false)?.value,
                    range: new vscode.Range(start, this.toker.range().start),
                    uri: this.uri
                };
                break;
            case 'float':
                if (this.toker.next() == '#') this.toker.next();
                result = {
                    kind: '#',
                    value: this.parseUniExpr(false)?.value,
                    range: new vscode.Range(start, this.toker.range().start),
                    uri: this.uri
                };
                break;
            case 'str':
                if (this.toker.next() == '$') this.toker.next();
                result = {
                    kind: '$',
                    value: this.parseUniExpr(false)?.value?.toString(),
                    range: new vscode.Range(start, this.toker.range().start),
                    uri: this.uri
                };
                break;
            case 'object':
                if (this.toker.next() == '.') this.toker.next();
                t = this.parseIdent();
                this.parseUniExpr(false);
                result = {
                    kind: '.',
                    range: new vscode.Range(start, this.toker.range().start),
                    uri: this.uri
                };
                break;
            case 'handle':
                this.toker.next();
                this.parseUniExpr(false);
                result = {
                    kind: '%',
                    range: new vscode.Range(start, this.toker.range().start),
                    uri: this.uri
                };
                break;
            case 'before':
                this.toker.next();
                this.parseUniExpr(false);
                result = {
                    kind: '.',
                    range: new vscode.Range(start, this.toker.range().start),
                    uri: this.uri
                };
                break;
            case 'after':
                this.toker.next();
                this.parseUniExpr(false);
                result = {
                    kind: '.',
                    range: new vscode.Range(start, this.toker.range().start),
                    uri: this.uri
                };
                break;
            case '+':
            case '-':
            case 'abs':
            case 'sgn':
                this.toker.next();
                const expr = this.parseUniExpr(false);
                result = {
                    kind: expr?.kind || '%',
                    value: expr?.value, // value calculated at analyze stage
                    range: new vscode.Range(start, this.toker.range().start),
                    uri: this.uri
                };
                break;
            case '~':
                this.toker.next();
                result = {
                    kind: '%',
                    lhs: this.parseUniExpr(false),
                    range: new vscode.Range(start, this.toker.range().start),
                    uri: this.uri // value calculated at analyze stage
                };
                break;
            default:
                result = this.parsePrimary(optional);
                break;
        }
        return result;
    }

    parsePrimary(optional: boolean): bb.Expression | undefined {
        const start = this.toker.range().start;
        let expression, t, ident, tag, result: bb.Expression | undefined;
        switch (this.toker.curr()) {
            case '(':
                this.toker.next();
                expression = this.parseExpr(false);
                if (this.toker.curr() != ')') this.expecting("')'");
                else this.toker.next();
                result = expression;
                break;
            case 'new':
                this.toker.next();
                t = this.parseIdent();
                result = {
                    kind: '.',
                    type: t.ident,
                    range: new vscode.Range(start, this.toker.range().end),
                    uri: this.uri
                };
                break;
            case 'first':
                this.toker.next();
                t = this.parseIdent();
                result = {
                    kind: '.',
                    type: t.ident,
                    range: new vscode.Range(start, this.toker.range().end),
                    uri: this.uri
                };
                break;
            case 'last':
                this.toker.next();
                t = this.parseIdent();
                result = {
                    kind: '.',
                    type: t.ident,
                    range: new vscode.Range(start, this.toker.range().end),
                    uri: this.uri
                };
                break;
            case 'null':
                result = {
                    kind: '.',
                    range: new vscode.Range(start, this.toker.range().end),
                    uri: this.uri
                };
                this.toker.next();
                break;
            case 'intconst':
                result = {
                    kind: '%',
                    value: parseInt(this.toker.text(), 10),
                    range: new vscode.Range(start, this.toker.range().end),
                    uri: this.uri
                };
                this.toker.next();
                break;
            case 'floatconst':
                result = {
                    kind: '#',
                    value: parseFloat(this.toker.text()),
                    range: new vscode.Range(start, this.toker.range().end),
                    uri: this.uri
                };
                this.toker.next();
                break;
            case 'stringconst':
                result = {
                    kind: '$',
                    value: this.toker.text(),
                    range: new vscode.Range(start, this.toker.range().end),
                    uri: this.uri
                };
                this.toker.next();
                break;
            case 'binconst':
                result = {
                    kind: '%',
                    value: parseInt(this.toker.text(), 2),
                    range: new vscode.Range(start, this.toker.range().end),
                    uri: this.uri
                };
                this.toker.next();
                break;
            case 'hexconst':
                result = {
                    kind: '%',
                    value: parseInt(this.toker.text(), 16),
                    range: new vscode.Range(start, this.toker.range().end),
                    uri: this.uri
                };
                this.toker.next();
                break;
            case 'pi':
                result = {
                    kind: '#',
                    value: 3.1415926535897932384626433832795,
                    range: new vscode.Range(start, this.toker.range().end),
                    uri: this.uri
                };
                this.toker.next();
                break;
            case 'true':
                result = {
                    kind: '?',
                    value: true,
                    range: new vscode.Range(start, this.toker.range().end),
                    uri: this.uri
                };
                this.toker.next();
                break;
            case 'false':
                result = {
                    kind: '?',
                    value: false,
                    range: new vscode.Range(start, this.toker.range().end),
                    uri: this.uri
                };
                this.toker.next();
                break;
            case 'ident':
                ident = this.parseIdent();
                tag = this.parseTypeTag(); // TODO convert to kind
                if (this.toker.curr() == '(' && !this.arrayDecls.has(ident.ident)) {
                    this.toker.next();
                    this.parseExprSeq();
                    if (this.toker.curr() != ')') this.expecting("')'");
                    this.toker.next();
                    result = {
                        kind: '.', // TODO get return value
                        type: tag,
                        range: new vscode.Range(start, this.toker.range().end),
                        uri: this.uri
                    };
                } else {
                    result = { ...this.parseVar(ident, tag), kind: '.' };
                }
                break;
            default:
                if (!optional) this.expecting('expression');
        }
        return result;
    }
}

function checkForArithmeticErrors(c: string, lhs: bb.Expression, rhs: bb.Expression, diagnostics: vscode.Diagnostic[]) {
    if (lhs.kind == '.') {
        diagnostics.push({
            message: `Illegal operation '${c}' for custom type ${lhs.type ?? ''}`,
            range: lhs.range,
            severity: vscode.DiagnosticSeverity.Error
        });
    } else if (lhs.kind == '?') {
        diagnostics.push({
            message: 'Converting logical value to numeric',
            range: lhs.range,
            severity: vscode.DiagnosticSeverity.Warning
        });
    }
    if (rhs.kind == '.') {
        diagnostics.push({
            message: `Illegal operation '${c}' for custom type ${rhs.type ?? ''}`,
            range: lhs.range,
            severity: vscode.DiagnosticSeverity.Error
        });
    } else if (rhs.kind == '?') {
        diagnostics.push({
            message: 'Converting logical value to numeric',
            range: rhs.range,
            severity: vscode.DiagnosticSeverity.Warning
        });
    }
}
