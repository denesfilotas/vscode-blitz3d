import { readFileSync } from 'fs';
import { isAbsolute, join } from 'path';
import * as vscode from 'vscode';
import { isBuiltinBlitzFunction, isIllegalTypeConversion, isTerm } from '../util/functions';
import { BlitzToker } from '../util/toker';
import * as bb from './types';
import { obtainWorkingDir, userLibs } from './context';

export interface Analyzer {
    analyze(intext: string, uri: vscode.Uri, parsed: bb.ParseResult): bb.AnalyzeResult;
    getResults(): bb.AnalyzeResult;
}

export class BlitzAnalyzer implements Analyzer {

    uri: vscode.Uri;
    workdir: string;

    consts: bb.Variable[] = [];
    globals: bb.Variable[] = [];
    locals: bb.Variable[] = [];
    structs: bb.Type[] = [];
    funcs: bb.Function[] = [];
    datas: any[] = [];
    labels: bb.Token[] = [];
    stmts: any[] = [];
    diagnostics: Map<vscode.Uri, vscode.Diagnostic[]>;

    arrayDecls: Map<string, bb.DimmedArray>;
    included: Set<string> = new Set<string>();

    toker: BlitzToker;

    // for check of return statements
    funtag: string = '';

    constructor(intext: string, uri: vscode.Uri, parsed: bb.ParseResult) {
        this.toker = new BlitzToker(intext);
        this.uri = uri;
        this.workdir = obtainWorkingDir(uri);
        this.consts = parsed.consts;
        this.globals = parsed.globals;
        this.arrayDecls = parsed.arrayDecls;
        this.locals = [];
        this.structs = parsed.structs;
        this.funcs = parsed.funcs;
        this.datas = parsed.datas;
        this.labels = parsed.labels;
        this.diagnostics = new Map<vscode.Uri, vscode.Diagnostic[]>();
        this.diagnostics.set(uri, []);
        this.parseStmtSeq('prog', this.locals);
    }

    getResults(): bb.AnalyzeResult {
        return this;
    }

    analyze(intext: string, uri: vscode.Uri, parsed: bb.ParseResult): bb.AnalyzeResult {
        this.toker = new BlitzToker(intext);
        this.uri = uri;
        this.workdir = obtainWorkingDir(uri);
        this.consts = parsed.consts;
        this.globals = parsed.globals;
        this.arrayDecls = parsed.arrayDecls;
        this.locals = [];
        this.structs = parsed.structs;
        this.funcs = parsed.funcs;
        this.datas = parsed.datas;
        this.labels = parsed.labels;
        this.diagnostics = new Map<vscode.Uri, vscode.Diagnostic[]>();
        this.diagnostics.set(this.uri, []);

        this.parseStmtSeq('prog', this.locals);

        return this;
    }

    private parseIdent(): bb.Ident {
        if (this.toker.curr() != 'ident') {
            return { ident: '<unnamed>', name: '<unnamed>' };
        }
        const ident = this.toker.text().toLowerCase();
        const name = this.toker.text();
        this.toker.next();
        return { ident, name };
    }

    private parseChar(c: string) {
        this.toker.next();
    }

    private parseStmtSeq(scope: 'prog' | 'block' | 'fun' | 'line', context: bb.Variable[]) {
        for (; ;) {
            while (this.toker.curr() == ':' || (scope != 'line' && this.toker.curr() == '\n')) {
                this.toker.next();
            }
            let pos = this.toker.range();
            switch (this.toker.curr()) {
                case 'include':
                    this.toker.next();
                    const inc = this.toker.text();
                    this.toker.next();
                    const infile = inc.substring(1, inc.length - 1).toLowerCase();
                    const infilepath = isAbsolute(infile) ? infile : join(this.workdir, infile);
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
                        this.parseStmtSeq(scope, context);
                        this.toker = t_toker;
                        this.uri = t_uri;
                    } catch (err) { /*console.error(err);*/ }
                    break;
                case 'ident':
                    {
                        const ident = this.parseIdent();
                        const trange = this.toker.range();
                        const tag = this.parseTypeTag();
                        if (tag && !'%#$'.includes(tag) && !this.structs.find(type => type.ident == tag)) this.diagnostics.get(this.uri)?.push({
                            message: `Unknown type '${tag}'`,
                            range: new vscode.Range(trange.end, this.toker.range().start),
                            severity: vscode.DiagnosticSeverity.Error
                        });
                        if (!this.arrayDecls.has(ident.ident) && this.toker.curr() != '=' && this.toker.curr() != '\\' && this.toker.curr() != '[') {
                            if (!isBuiltinBlitzFunction(ident.ident) && !this.funcs.concat(userLibs).find(fun => fun.ident == ident.ident)) this.diagnostics.get(this.uri)?.push({
                                message: `Unknown function '${ident.name}'`,
                                range: pos,
                                severity: vscode.DiagnosticSeverity.Error
                            });
                            if (this.toker.curr() == '(') {
                                let nest = 1, k;
                                for (k = 1; ; ++k) {
                                    const c = this.toker.lookAhead(k);
                                    if (isTerm(c)) {
                                        break;
                                    } else if (c == '(') ++nest;
                                    else if (c == ')' && --nest == 0) break;
                                }
                                if (isTerm(this.toker.lookAhead(++k))) {
                                    this.toker.next();
                                    this.parseExprSeq(context);
                                    this.toker.next();
                                } else {
                                    this.parseExprSeq(context);
                                }
                            } else {
                                this.parseExprSeq(context);
                            }
                        } else {
                            const variable = this.parseVar(true, context, ident, tag, pos);
                            const oldVar = this.arrayDecls.get(variable.ident) ?? context.concat(this.globals, this.consts).find(local => local.ident == variable.ident);
                            const vartag = variable.tag || oldVar?.tag || '%';
                            if (!oldVar && variable.kind != 'field' && variable.kind != 'array') context.push(variable);
                            this.toker.next();
                            const expression = this.parseExpr(context);
                            const exprtag = expression?.kind.replace('.', expression.type ?? '') || '%';
                            const illegal = isIllegalTypeConversion(exprtag, vartag);
                            if (illegal) this.diagnostics.get(this.uri)?.push({
                                message: `Expression of type '${exprtag}' ${illegal == 1 ? 'should not' : 'cannot'} be assigned to variable of type '${vartag}'`,
                                range: expression?.range ?? pos,
                                severity: illegal == 1 ? vscode.DiagnosticSeverity.Warning : vscode.DiagnosticSeverity.Error
                            });
                            if (!tag || (tag == '%' && exprtag == '?')) variable.tag = exprtag;
                        }
                    }
                    break;
                case 'if':
                    {
                        this.toker.next();
                        this.parseIf(scope == 'fun' ? 'fun' : 'block', context);
                        if (this.toker.curr() == 'endif') this.toker.next();
                    }
                    break;
                case 'while':
                    {
                        this.toker.next();
                        this.parseExpr(context);
                        this.parseStmtSeq(scope == 'fun' ? 'fun' : 'block', context);
                        this.toker.next();
                    }
                    break;
                case 'repeat':
                    {
                        this.toker.next();
                        this.parseStmtSeq(scope == 'fun' ? 'fun' : 'block', context);
                        const curr = this.toker.curr();
                        this.toker.next();
                        if (curr == 'until') this.parseExpr(context);
                    }
                    break;
                case 'select':
                    {
                        this.toker.next();
                        this.parseExpr(context);
                        const nodes: any[] = [];
                        for (; ;) {
                            while (isTerm(this.toker.curr())) this.toker.next();
                            if (this.toker.curr() == 'case') {
                                this.toker.next();
                                const expressions = this.parseExprSeq(context);
                                this.parseStmtSeq(scope == 'fun' ? 'fun' : 'block', context);
                                for (const expr of expressions) {
                                    if (!expr.value) continue;
                                    if (nodes.includes(expr.value)) this.diagnostics.get(this.uri)?.push({
                                        message: `Duplicate case '${expr.value}'`,
                                        range: expr.range,
                                        severity: vscode.DiagnosticSeverity.Warning
                                    });
                                    nodes.push(expr.value);
                                }
                                continue;
                            } else if (this.toker.curr() == 'default') {
                                this.toker.next();
                                this.parseStmtSeq(scope == 'fun' ? 'fun' : 'block', context);
                                break;
                            }
                            break;
                        }
                        this.toker.next();
                    }
                    break;
                case 'for':
                    {
                        this.toker.next();
                        const variable = this.parseVar(true, context);
                        if (context.concat(this.globals, this.consts).find(local => local.ident == variable.ident)) {
                            this.diagnostics.get(this.uri)?.push({
                                message: `Overwriting variable '${variable.name}'`,
                                range: variable.range,
                                severity: vscode.DiagnosticSeverity.Hint
                            });
                        } else {
                            context.push(variable);
                        }
                        if (this.toker.next() == 'each') {
                            this.toker.next();
                            const ident = this.parseIdent();
                            if (!this.structs.find(type => type.ident == ident.ident)) this.diagnostics.get(this.uri)?.push({
                                message: `Unknown type '${ident.name}'`,
                                range: this.toker.range(),
                                severity: vscode.DiagnosticSeverity.Error
                            });
                            this.parseStmtSeq(scope == 'fun' ? 'fun' : 'block', context);
                            this.toker.next();
                        } else {
                            this.parseExpr(context);
                            this.toker.next();
                            this.parseExpr(context);
                            if (this.toker.curr() == 'step') {
                                this.toker.next();
                                this.parseExpr(context);
                            }
                            this.parseStmtSeq(scope == 'fun' ? 'fun' : 'block', context);
                            this.toker.next();
                        }
                    }
                    break;
                case 'exit':
                    this.toker.next();
                    break;
                case 'goto':
                    {
                        this.toker.next();
                        const range = this.toker.range();
                        const t = this.parseIdent();
                        if (!this.labels.find(label => label.ident == t.ident)) this.diagnostics.get(this.uri)?.push({
                            message: `Unknown label '${t.name}'`,
                            range: range,
                            severity: vscode.DiagnosticSeverity.Error
                        });
                    }
                    break;
                case 'gosub':
                    {
                        this.toker.next();
                        const range = this.toker.range();
                        const t = this.parseIdent();
                        if (!this.labels.find(label => label.ident == t.ident)) this.diagnostics.get(this.uri)?.push({
                            message: `Unknown label '${t.name}'`,
                            range: range,
                            severity: vscode.DiagnosticSeverity.Error
                        });
                    }
                    break;
                case 'return':
                    {
                        // if (scope != 'fun') this.diagnostics.get(this.uri)?.push({
                        //     message: 'Cannot return from outside of a function',
                        //     range: this.toker.range(),
                        //     severity: vscode.DiagnosticSeverity.Error
                        // });
                        this.toker.next();
                        const expr = this.parseExpr(context);
                        if (expr?.kind) {
                            const exprtag = expr.kind.replace('.', expr.type ?? '');
                            if (exprtag && expr.kind != '?' && !this.funtag) this.diagnostics.get(this.uri)?.push({
                                message: `Return type '${exprtag}' should be indicated at function definition`,
                                range: expr.range,
                                severity: exprtag == '%' ? vscode.DiagnosticSeverity.Warning : vscode.DiagnosticSeverity.Error
                            });
                            if (exprtag && this.funtag) {
                                const illegal = isIllegalTypeConversion(exprtag, this.funtag);
                                if (illegal) {
                                    this.diagnostics.get(this.uri)?.push({
                                        message: `Function with return type '${this.funtag || '%'}' ${illegal == 1 ? 'should not' : 'cannot'} return type '${exprtag}'`,
                                        range: expr.range,
                                        severity: illegal == 1 ? vscode.DiagnosticSeverity.Warning : vscode.DiagnosticSeverity.Error
                                    });
                                }
                            }
                        }
                    }
                    break;
                case 'delete':
                    {
                        if (this.toker.next() == 'each') {
                            this.toker.next();
                            const t = this.parseIdent();
                            if (!this.structs.find(type => type.ident == t.ident)) this.diagnostics.get(this.uri)?.push({
                                message: `Unknown type '${t.name}'`,
                                range: this.toker.range(),
                                severity: vscode.DiagnosticSeverity.Error
                            });
                        } else {
                            this.parseExpr(context);
                        }
                    }
                    break;
                case 'insert':
                    {
                        this.toker.next();
                        this.parseExpr(context);
                        this.toker.next();
                        this.parseExpr(context);
                    }
                    break;
                case 'read':
                    do {
                        this.toker.next();
                        const variable = this.parseVar(false, context);
                        this.stmts.push({
                            kind: 'read',
                            range: pos
                        });
                        pos = this.toker.range();
                    } while (this.toker.curr() == ',');
                    break;
                case 'restore':
                    if (this.toker.next() == 'ident') {
                        if (!this.labels.find(label => label.ident == this.toker.text().toLowerCase())) this.diagnostics.get(this.uri)?.push({
                            message: `Unknown label '${this.toker.text()}'`,
                            range: this.toker.range(),
                            severity: vscode.DiagnosticSeverity.Error
                        });
                    }
                    this.toker.next();
                    break;
                case 'data':
                    do {
                        this.toker.next();
                        this.datas.push({
                            kind: 'data', // TODO
                            expression: this.parseExpr(context)
                        });
                    } while (this.toker.curr() == ',');
                    break;
                case 'type':
                    this.parseStructDecl(context);
                    break;
                case 'const':
                    do {
                        const start = this.toker.range().start;
                        this.toker.next();
                        // this.consts = this.consts.filter(c => c.ident != variable.ident);
                        const variable = this.parseVarDecl('global', true, context, start);
                        const constant = this.consts.find(c => c.ident == variable.ident);
                        if (constant) Object.assign(constant, variable);
                        else this.consts.push(variable);
                    } while (this.toker.curr() == ',');
                    break;
                case 'function':
                    const fun = this.parseFuncDecl(context);
                    const prev = this.funcs.find(f => f.ident == fun.ident);
                    if (prev) Object.assign(prev, fun);
                    else this.funcs.push(fun);
                    break;
                case 'dim':
                    do {
                        this.toker.next();
                        this.parseArrayDecl(context);
                    } while (this.toker.curr() == ',');
                    break;
                case 'local':
                    do {
                        const start = this.toker.range().start;
                        this.toker.next();
                        const variable = this.parseVarDecl('local', false, context, start);
                        context.push(variable);
                    } while (this.toker.curr() == ',');
                    break;
                case 'global':
                    // if (scope != 'prog') this.exception('global variables can only be declared in the main program');
                    do {
                        const start = this.toker.range().start;
                        this.toker.next();
                        this.parseVarDecl('global', false, context, start);
                    } while (this.toker.curr() == ',');
                    break;
                case '.':
                    this.toker.next();
                    this.parseIdent();
                    break;
                case 'eof':
                    return;
                default:
                    if (scope != 'prog') return;
                    this.toker.next();
                    break;
            }
        }
    }

    private parseTypeTag(): string {
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

    private parseVar(allowImplicit: boolean, context: bb.Variable[], ident?: bb.Ident, tag?: string, range?: vscode.Range): bb.Variable {
        if (!ident && !tag && !range) {
            range = this.toker.range();
            ident = this.parseIdent();
            tag = this.parseTypeTag();
        }
        let variable: bb.Variable | undefined = context.find(v => v.ident == ident?.ident) ?? this.consts.find(c => c.ident == ident?.ident) ?? this.globals.find(v => v.ident == ident?.ident) ?? this.arrayDecls.get(ident?.ident ?? '');
        if (!variable && !allowImplicit) {
            this.diagnostics.get(this.uri)?.push({
                message: 'Implicit variable declaration',
                range: range!,
                severity: vscode.DiagnosticSeverity.Hint
            });
        }
        const illegal = isIllegalTypeConversion(variable?.tag || '%', tag || '%');
        if (tag && variable && illegal) this.diagnostics.get(this.uri)?.push({
            message: `Variable of type '${variable.tag || '%'}' ${illegal == 1 ? 'should not' : 'cannot'} be converted to type '${tag}'`,
            range: range!,
            severity: illegal == 1 ? vscode.DiagnosticSeverity.Warning : vscode.DiagnosticSeverity.Error
        });
        if (this.toker.curr() == '(') {
            this.toker.next();
            this.parseExprSeq(context);
            this.toker.next();
            variable = variable ?? {
                kind: 'array',
                ...ident!,
                tag: tag!,
                constant: false,
                range: this.toker.range(),
                declarationRange: this.toker.range(),
                uri: this.uri,
            };
        } else {
            variable = variable ?? {
                kind: 'variable',
                ...ident!,
                tag: tag!,
                constant: false,
                range: this.toker.range(),
                declarationRange: this.toker.range(),
                uri: this.uri
            };
        }

        for (; ;) {
            if (this.toker.curr() == '\\') {
                this.toker.next();
                const range = this.toker.range();
                ident = this.parseIdent();
                tag = this.parseTypeTag();
                const type = this.structs.find(type => type.ident == variable!.tag);
                const f = type?.fields.find(field => field.ident == ident?.ident);
                if (f) variable = f;
                else this.diagnostics.get(this.uri)?.push({
                    message: `Unknown field '${ident?.name}'`,
                    range: range,
                    severity: vscode.DiagnosticSeverity.Error
                });
                const illegal = isIllegalTypeConversion(variable?.tag || '%', tag || '%');
                if (f && tag && variable && illegal) this.diagnostics.get(this.uri)?.push({
                    message: `Variable of type '${variable.tag || '%'}' ${illegal == 1 ? 'should not' : 'cannot'} be converted to type '${tag}'`,
                    range: range,
                    severity: illegal == 1 ? vscode.DiagnosticSeverity.Warning : vscode.DiagnosticSeverity.Error
                });

            } else if (this.toker.curr() == '[') {
                this.toker.next();
                this.parseExprSeq(context);
                this.toker.next();
            } else {
                break;
            }
        }
        return { ...variable, range: range!, declarationRange: range! };
    }

    private parseVarDecl(kind: 'local' | 'global' | 'param' | 'field', constant: boolean, context: bb.Variable[], start: vscode.Position): bb.Variable {
        const range = this.toker.range();
        const ident = this.parseIdent();
        const trange = this.toker.range();
        const tag = this.parseTypeTag() || '%';
        if (!'%#$'.includes(tag) && !this.structs.find(type => type.ident == tag)) this.diagnostics.get(this.uri)?.push({
            message: `Unknown type '${tag}'`,
            range: new vscode.Range(trange.end, this.toker.range().start),
            severity: vscode.DiagnosticSeverity.Error
        });
        if (this.toker.curr() == '[') {
            // if (constant) this.exception('Blitz arrays cannot be constant');
            this.toker.next();
            this.parseExprSeq(context);
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
                expr = this.parseExpr(context) as bb.Expression;
                const exprtag = expr.kind.replace('.', expr.type ?? '') || '%';
                const illegal = isIllegalTypeConversion(exprtag, tag);
                if (tag && illegal) this.diagnostics.get(this.uri)?.push({
                    message: `Expression of type '${exprtag}' ${illegal == 1 ? 'should not' : 'cannot'} be assigned to variable of type '${tag}'`,
                    range: expr.range,
                    severity: illegal == 1 ? vscode.DiagnosticSeverity.Warning : vscode.DiagnosticSeverity.Error
                });
                // if (!tag) tag = expr?.kind ?? '%';
            } else if (constant) {
                // this.exception('constants must be initialized');
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

    private parseArrayDecl(context: bb.Variable[]): bb.DimmedArray | undefined {
        const range = this.toker.range();
        const ident = this.parseIdent();
        if (!ident) return;
        const tag = this.parseTypeTag();
        this.toker.next();
        const expressions = this.parseExprSeq(context);
        // if (expressions.length == 0) this.exception('arrays must have at least one dimension');
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

    private parseFuncDecl(context: bb.Variable[]): bb.Function {
        const declRangeStart = this.toker.range().start;
        this.toker.next();
        const range = this.toker.range();
        const ident = this.parseIdent();
        const tag = this.parseTypeTag();
        this.funtag = tag;
        const params = [];
        if (this.toker.next() != ')') {
            for (; ;) {
                params.push(this.parseVarDecl('param', false, context, this.toker.range().start));
                if (this.toker.curr() != ',') break;
                this.toker.next();
            }
        }
        this.toker.next();
        const locals = [...params];
        this.parseStmtSeq('fun', locals);
        locals.shift();
        const declRangeEnd = this.toker.range().end;
        this.toker.next();
        this.funtag = '';
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

    private parseStructDecl(context: bb.Variable[]): bb.Type {
        const pos = this.toker.range().start;
        this.toker.next();
        const range = this.toker.range();
        const ident = this.parseIdent();
        while (this.toker.curr() == '\n') this.toker.next();
        const fields: bb.Variable[] = [];
        while (this.toker.curr() == 'field') {
            const lineFields = [];
            do {
                this.toker.next();
                const field = this.parseVarDecl('field', false, context, range.start);
                if (!fields.find(f => f.ident == field.ident)) lineFields.push(field);
            } while (this.toker.curr() == ',');
            if (this.toker.text().startsWith(';')) {
                for (const field of lineFields) field.description = this.toker.text().substring(1).trim();
            }
            fields.push(...lineFields);
            while (this.toker.curr() == '\n') this.toker.next();
        }
        this.toker.next();
        const type = {
            kind: 'type',
            ...ident,
            fields: fields,
            range: new vscode.Range(pos, this.toker.range().start),
            declarationRange: range,
            uri: this.uri,
        };
        return type;
    }

    private parseIf(scope: 'block' | 'fun', context: bb.Variable[]): any {
        let expr, stmts, elseOpt;
        expr = this.parseExpr(context);
        if (this.toker.curr() == 'then') this.toker.next();
        const blockIf = isTerm(this.toker.curr());
        stmts = this.parseStmtSeq(blockIf ? scope == 'fun' ? 'fun' : 'block' : 'line', context);

        if (this.toker.curr() == 'elseif') {
            // const range = this.toker.range();
            this.toker.next();
            const ifNode = this.parseIf(scope, context);
            // ifNode.range.start = range.start;
            elseOpt = [];
            elseOpt.push(ifNode);
        } else if (this.toker.curr() == 'else') {
            this.toker.next();
            elseOpt = this.parseStmtSeq(blockIf ? scope == 'fun' ? 'fun' : 'block' : 'line', context);
        }

        return {
            kind: 'if',
            expr: expr,
            stmts: stmts,
            elseOpt: elseOpt,
            range: this.toker.range()
        };
    }

    private parseExprSeq(context: bb.Variable[]) {
        const expressions = [];
        let optional = true;
        for (let e; e = this.parseExpr(context);) {
            expressions.push(e);
            if (this.toker.curr() != ',') break;
            this.toker.next();
            optional = false;
        }
        return expressions;
    }

    private parseExpr(context: bb.Variable[]): bb.Expression | undefined {
        const start = this.toker.range().start;
        if (this.toker.curr() == 'not') {
            this.toker.next();
            const lhs = this.parseExpr1(context);
            if (!lhs) return;
            const value = lhs.value == undefined ? undefined : lhs.value == 0;
            return {
                kind: '?',
                lhs: lhs,
                value: value,
                range: new vscode.Range(start, this.toker.range().start),
                uri: this.uri
            };
        }
        return this.parseExpr1(context);
    }

    private parseExpr1(context: bb.Variable[]): bb.Expression | undefined {
        const start = this.toker.range().start;
        let lhs = this.parseExpr2(context);
        if (!lhs) return;
        for (; ;) {
            const c = this.toker.curr();
            if (c != 'and' && c != 'or' && c != 'xor') return lhs;
            this.toker.next();
            const rhs = this.parseExpr2(context);
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

    private parseExpr2(context: bb.Variable[]): bb.Expression | undefined {
        const start = this.toker.range().start;
        let lhs = this.parseExpr3(context);
        if (!lhs) return;
        for (; ;) {
            const c = this.toker.curr();
            if (c != '<' && c != '>' && c != '=' && c != 'le' && c != 'ge' && c != 'ne') return lhs;
            this.toker.next();
            const rhs = this.parseExpr3(context);
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
                uri: this.uri,
                value: value
            };
        }
    }

    private parseExpr3(context: bb.Variable[]): bb.Expression | undefined {
        const start = this.toker.range().start;
        let lhs = this.parseExpr4(context);
        if (!lhs) return;
        for (; ;) {
            const c = this.toker.curr();
            if (c != '+' && c != '-') return lhs;
            this.toker.next();
            const rhs = this.parseExpr4(context);
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
                kind: getArithmeticType(lhs?.kind || '%', rhs?.kind || '%'),
                lhs: lhs,
                rhs: rhs,
                range: new vscode.Range(start, this.toker.range().start),
                uri: this.uri,
                value: value
            };
        }
    }

    private parseExpr4(context: bb.Variable[]): bb.Expression | undefined {
        const start = this.toker.range().start;
        let lhs = this.parseExpr5(context);
        if (!lhs) return;
        for (; ;) {
            const c = this.toker.curr();
            if (c != 'shl' && c != 'shr' && c != 'sar') return lhs;
            this.toker.next();
            const rhs = this.parseExpr5(context);
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
                kind: '%', // TODO type check
                lhs: lhs,
                rhs: rhs,
                range: new vscode.Range(start, this.toker.range().start),
                uri: this.uri,
                value: value
            };
        }
    }

    private parseExpr5(context: bb.Variable[]): bb.Expression | undefined {
        const start = this.toker.range().start;
        let lhs = this.parseExpr6(context);
        if (!lhs) return;
        for (; ;) {
            const c = this.toker.curr();
            if (c != '*' && c != '/' && c != 'mod') return lhs;
            this.toker.next();
            const rhs = this.parseExpr6(context);
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
                kind: getArithmeticType(lhs.kind, rhs?.kind ?? '%'),
                lhs: lhs,
                rhs: rhs,
                range: new vscode.Range(start, this.toker.range().start),
                uri: this.uri,
                value: value
            };
        }
    }

    private parseExpr6(context: bb.Variable[]): bb.Expression | undefined {
        const start = this.toker.range().start;
        let lhs = this.parseUniExpr(context);
        if (!lhs) return;
        for (; ;) {
            const c = this.toker.curr();
            if (c != '^') return lhs;
            this.toker.next();
            const rhs = this.parseUniExpr(context);
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

    private parseUniExpr(context: bb.Variable[]): bb.Expression {
        let result: bb.Expression | undefined;
        let t;
        const c = this.toker.curr();
        const start = this.toker.range().start;
        // TODO implement type checks
        switch (c) {
            case 'int':
                if (this.toker.next() == '%') this.toker.next();
                result = {
                    kind: '%',
                    value: this.parseUniExpr(context)?.value,
                    range: new vscode.Range(start, this.toker.range().start),
                    uri: this.uri
                };
                break;
            case 'float':
                if (this.toker.next() == '#') this.toker.next();
                result = {
                    kind: '#',
                    value: this.parseUniExpr(context)?.value,
                    range: new vscode.Range(start, this.toker.range().start),
                    uri: this.uri
                };
                break;
            case 'str':
                if (this.toker.next() == '$') this.toker.next();
                result = {
                    kind: '$',
                    value: this.parseUniExpr(context)?.value?.toString(),
                    range: new vscode.Range(start, this.toker.range().start),
                    uri: this.uri
                };
                break;
            case 'object':
                if (this.toker.next() == '.') this.toker.next();
                t = this.parseIdent();
                this.parseUniExpr(context);
                result = {
                    kind: '.',
                    type: t.ident,
                    range: new vscode.Range(start, this.toker.range().start),
                    uri: this.uri
                };
                break;
            case 'handle':
                this.toker.next();
                this.parseUniExpr(context);
                result = {
                    kind: '%',
                    range: new vscode.Range(start, this.toker.range().start),
                    uri: this.uri
                };
                break;
            case 'before':
                {
                    this.toker.next();
                    const expr = this.parseUniExpr(context);
                    if (expr.kind != '.') this.diagnostics.get(this.uri)?.push({
                        message: `Variable of type '${expr.kind}' cannot be used in 'Before' expression`,
                        range: expr.range,
                        severity: vscode.DiagnosticSeverity.Error
                    });
                    result = {
                        kind: '.',
                        type: expr.type || '',
                        range: new vscode.Range(start, this.toker.range().start),
                        uri: this.uri
                    };
                    break;
                }
            case 'after':
                this.toker.next();
                const expr = this.parseUniExpr(context);
                if (expr.kind != '.') this.diagnostics.get(this.uri)?.push({
                    message: `Variable of type '${expr.kind}' cannot be used in 'After' expression`,
                    range: expr.range,
                    severity: vscode.DiagnosticSeverity.Error
                });
                result = {
                    kind: '.',
                    type: expr.type || '',
                    range: new vscode.Range(start, this.toker.range().start),
                    uri: this.uri
                };
                break;
            case '+':
                {
                    this.toker.next();
                    const expr = this.parseUniExpr(context);
                    result = {
                        kind: expr?.kind || '%',
                        value: expr?.value ? +expr.value : undefined,
                        range: new vscode.Range(start, this.toker.range().start),
                        uri: this.uri
                    };
                    break;
                }
            case '-':
                {
                    this.toker.next();
                    const expr = this.parseUniExpr(context);
                    result = {
                        kind: expr?.kind || '%',
                        value: expr?.value ? -expr.value : undefined,
                        range: new vscode.Range(start, this.toker.range().start),
                        uri: this.uri
                    };
                    break;
                }
            case 'abs':
                {
                    this.toker.next();
                    const expr = this.parseUniExpr(context);
                    result = {
                        kind: expr?.kind || '%',
                        value: expr?.value ? Math.abs(expr.value) : undefined,
                        range: new vscode.Range(start, this.toker.range().start),
                        uri: this.uri
                    };
                    break;
                }
            case 'sgn':
                {
                    this.toker.next();
                    const expr = this.parseUniExpr(context);
                    result = {
                        kind: expr?.kind || '%',
                        value: expr?.value ? Math.sign(expr.value) : undefined,
                        range: new vscode.Range(start, this.toker.range().start),
                        uri: this.uri
                    };
                    break;
                }
            case '~':
                this.toker.next();
                result = {
                    kind: '%',
                    lhs: this.parseUniExpr(context),
                    range: new vscode.Range(start, this.toker.range().start),
                    uri: this.uri // TODO set value
                };
                break;
            default:
                result = this.parsePrimary(context);
                break;
        }
        return result!;
    }

    private parsePrimary(context: bb.Variable[]): bb.Expression | undefined {
        const start = this.toker.range().start;
        let expression, t: bb.Ident, tag, result: bb.Expression | undefined;
        switch (this.toker.curr()) {
            case '(':
                this.toker.next();
                expression = this.parseExpr(context);
                if (this.toker.curr() == ')') this.toker.next();
                result = expression;
                break;
            case 'new':
                this.toker.next();
                t = this.parseIdent();
                if (!this.structs.find(type => type.ident == t.ident)) this.diagnostics.get(this.uri)?.push({
                    message: `Unknown type '${t.name}'`,
                    range: this.toker.range(),
                    severity: vscode.DiagnosticSeverity.Error
                });
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
                if (!this.structs.find(type => type.ident == t.ident)) this.diagnostics.get(this.uri)?.push({
                    message: `Unknown type '${t.name}'`,
                    range: this.toker.range(),
                    severity: vscode.DiagnosticSeverity.Error
                });
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
                if (!this.structs.find(type => type.ident == t.ident)) this.diagnostics.get(this.uri)?.push({
                    message: `Unknown type '${t.name}'`,
                    range: this.toker.range(),
                    severity: vscode.DiagnosticSeverity.Error
                });
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
                    type: 'null',
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
                    value: parseInt(this.toker.text().substring(1), 2),
                    range: new vscode.Range(start, this.toker.range().end),
                    uri: this.uri
                };
                this.toker.next();
                break;
            case 'hexconst':
                result = {
                    kind: '%',
                    value: parseInt(this.toker.text().substring(1), 16),
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
                const pos = this.toker.range();
                t = this.parseIdent();
                tag = this.parseTypeTag();
                if (this.toker.curr() == '(' && !this.arrayDecls.has(t.ident)) {
                    const fun = this.funcs.concat(userLibs).find(fun => fun.ident == t.ident);
                    if (!fun && !isBuiltinBlitzFunction(t.ident)) this.diagnostics.get(this.uri)?.push({
                        message: `Unknown function '${t.name}'`,
                        range: pos,
                        severity: vscode.DiagnosticSeverity.Error
                    });
                    // const stub = stubs.find(s => s.name.toLowerCase() == t.ident);
                    // TODO embed return types in stubs
                    this.toker.next();
                    this.parseExprSeq(context);
                    this.toker.next();
                    if ((!tag && fun) || (tag == '%' && fun?.tag == '?')) tag = fun.tag;
                    if (['?', '%', '#', '$'].includes(tag)) result = {
                        ...fun,
                        kind: tag as bb.ExpressionKind,
                        range: new vscode.Range(start, this.toker.range().end),
                        uri: this.uri
                    };
                    else result = {
                        ...fun,
                        kind: '.',
                        type: tag,
                        range: new vscode.Range(start, this.toker.range().end),
                        uri: this.uri
                    };
                } else {
                    const variable = this.parseVar(false, context, t, tag, pos);
                    const illegal = isIllegalTypeConversion(variable.tag || '%', tag || '%');
                    if (tag && illegal) this.diagnostics.get(this.uri)?.push({
                        message: `Variable of type '${variable.tag || '%'}' ${illegal == 1 ? 'should not' : 'cannot'} be converted to type '${tag}'`,
                        range: variable.range,
                        severity: illegal == 1 ? vscode.DiagnosticSeverity.Warning : vscode.DiagnosticSeverity.Error
                    });
                    if (!tag || (tag == '%' && variable.tag == '?')) tag = variable.tag || '%';
                    if (['?', '%', '#', '$'].includes(tag)) result = { ...variable, kind: tag as bb.ExpressionKind };
                    else result = { ...variable, kind: '.', type: tag };
                }
                break;
            default:
            // if (!optional) this.expecting('expression');
        }
        return result;
    }
}

function getArithmeticType(lhs: string, rhs: string): '%' | '#' | '$' {
    if (lhs == '%' && rhs == '%') return '%';
    if (lhs == '#' || rhs == '#') return '#';
    if (lhs == '$' || rhs == '$') return '$';
    return '%';
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
