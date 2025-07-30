import * as vscode from 'vscode';
import { parsed } from '../context/context';
import { isTerm } from '../util/functions';
import { BlitzToker } from '../util/toker';

const tokenTypes = new Map<string, number>();
const tokenModifiers = new Map<string, number>();

interface IParsedToken {
    range: vscode.Range;
    tokenType: string;
    tokenModifiers: string[];
}

let tokens: IParsedToken[] = [];

export const legend = (function () {
    const tokenTypesLegend = [
        'comment', 'string', 'keyword', 'number', 'operator', 'type', 'typeParameter', 'function', 'variable', 'parameter', 'property', 'field', 'array'
    ];
    tokenTypesLegend.forEach((tokenType, index) => tokenTypes.set(tokenType, index));

    const tokenModifiersLegend = [
        'declaration', 'readonly', 'modification', 'deprecated', 'link'
    ];
    tokenModifiersLegend.forEach((tokenModifier, index) => tokenModifiers.set(tokenModifier, index));

    return new vscode.SemanticTokensLegend(tokenTypesLegend, tokenModifiersLegend);
})();

export default class BlitzSemanticTokensProvider implements vscode.DocumentSemanticTokensProvider {
    static arrayTokenType: string = '';

    async provideDocumentSemanticTokens(document: vscode.TextDocument, token: vscode.CancellationToken): Promise<vscode.SemanticTokens> {
        BlitzSemanticTokensProvider.arrayTokenType = vscode.workspace.getConfiguration('blitz3d.editor').get('DimmedArrayTokenType') || 'array';
        const allTokens = BlitzSemanticTokensProvider._parseText(document.getText());
        const builder = new vscode.SemanticTokensBuilder(legend);
        allTokens.forEach((token) => {
            builder.push(token.range, token.tokenType, token.tokenModifiers);
        });
        return builder.build();
    }

    static _parseText(text: string): IParsedToken[] {
        const toker = new BlitzToker(text);
        tokens = [];
        this.parseStmtSeq(toker, 'prog');
        return tokens;
    }

    static parseStmtSeq(toker: BlitzToker, scope: 'prog' | 'block' | 'fun' | 'line') {
        for (; ;) {
            while (toker.curr() == ':' || (scope != 'line' && toker.curr() == '\n')) toker.next();
            switch (toker.curr()) {
                case 'include':
                    toker.next();
                    tokens.push({
                        tokenType: 'string',
                        range: toker.range(),
                        tokenModifiers: ['link']
                    });
                    toker.next();
                    break;
                case 'ident':
                    {
                        const token: IParsedToken = {
                            tokenType: 'variable',
                            range: toker.range(),
                            tokenModifiers: []
                        };
                        const ident = toker.text().toLowerCase();
                        toker.next();
                        this.parseTypeTag(toker);
                        if (toker.curr() != '=' && toker.curr() != '\\' && toker.curr() != '[') {
                            token.tokenType = parsed.arrayDecls.has(ident) ? this.arrayTokenType : 'function';
                            if (parsed.funcs.find(fun => fun.ident == ident)?.deprecated !== undefined) token.tokenModifiers = ['deprecated'];
                            tokens.push(token);
                            if (toker.curr() == '(') {
                                let nest = 1, k;
                                for (k = 1; ; ++k) {
                                    const c = toker.lookAhead(k);
                                    if (isTerm(c)) {
                                        break;
                                    } else if (c == '(') ++nest;
                                    else if (c == ')' && --nest == 0) break;
                                }
                                if (isTerm(toker.lookAhead(++k))) {
                                    toker.next();
                                    this.parseExprSeq(toker);
                                    toker.next();
                                } else {
                                    this.parseExprSeq(toker);
                                }
                            } else {
                                this.parseExprSeq(toker);
                            }
                        } else {
                            tokens.push(token);
                            this.parseVar(toker, true, true);
                            toker.next();
                            this.parseExpr(toker);
                        }
                    }
                    break;
                case 'if':
                    {
                        toker.next();
                        this.parseIf(toker);
                        if (toker.curr() == 'endif') toker.next();
                    }
                    break;
                case 'while':
                    {
                        toker.next();
                        this.parseExpr(toker);
                        this.parseStmtSeq(toker, 'block');
                        if (toker.curr() == 'wend') toker.next();
                    }
                    break;
                case 'repeat':
                    {
                        toker.next();
                        this.parseStmtSeq(toker, 'block');
                        const curr = toker.curr();
                        toker.next();
                        if (curr == 'until') this.parseExpr(toker);
                    }
                    break;
                case 'select':
                    {
                        toker.next();
                        this.parseExpr(toker);
                        for (; ;) {
                            while (isTerm(toker.curr())) toker.next();
                            if (toker.curr() == 'case') {
                                toker.next();
                                this.parseExprSeq(toker);
                                this.parseStmtSeq(toker, 'block');
                                continue;
                            } else if (toker.curr() == 'default') {
                                toker.next();
                                this.parseStmtSeq(toker, 'block');
                                break;
                            } else if (toker.curr() == 'endselect') {
                                break;
                            }
                            break;
                        }
                        toker.next();
                    }
                    break;
                case 'for':
                    {
                        toker.next();
                        this.parseVar(toker);
                        if (toker.next() == 'each') {
                            toker.next();
                            tokens.push({
                                tokenType: 'type',
                                range: toker.range(),
                                tokenModifiers: []
                            });
                            toker.next();
                            this.parseStmtSeq(toker, 'block');
                            toker.next();
                        } else {
                            this.parseExpr(toker);
                            toker.next();
                            this.parseExpr(toker);
                            if (toker.curr() == 'step') {
                                toker.next();
                                this.parseExpr(toker);
                            }
                            this.parseStmtSeq(toker, 'block');
                            toker.next();
                        }
                    }
                    break;
                case 'exit':
                    toker.next();
                    break;
                case 'goto':
                case 'gosub':
                    toker.next();
                    tokens.push({
                        tokenType: 'string',
                        range: toker.range(),
                        tokenModifiers: []
                    });
                    toker.next();
                    break;
                case 'return':
                    toker.next();
                    this.parseExpr(toker);
                    break;
                case 'delete':
                    if (toker.next() == 'each') {
                        toker.next();
                        tokens.push({
                            tokenType: 'type',
                            range: toker.range(),
                            tokenModifiers: []
                        });
                        toker.next();
                    } else {
                        this.parseExpr(toker);
                    }
                    break;
                case 'insert':
                    toker.next();
                    this.parseExpr(toker);
                    toker.next();
                    this.parseExpr(toker);
                    break;
                case 'read':
                    do {
                        toker.next();
                        this.parseVar(toker);
                    } while (toker.curr() == ',');
                    break;
                case 'restore':
                    toker.next();
                    tokens.push({
                        tokenType: 'string',
                        range: toker.range(),
                        tokenModifiers: []
                    });
                    toker.next();
                    break;
                case 'data':
                    do {
                        toker.next();
                        this.parseExpr(toker);
                    } while (toker.curr() == ',');
                    break;
                case 'type':
                    this.parseStructDecl(toker);
                    break;
                case 'const':
                    do {
                        toker.next();
                        this.parseVarDecl(toker, 'global', true);
                    } while (toker.curr() == ',');
                    break;
                case 'function':
                    this.parseFuncDecl(toker);
                    break;
                case 'dim':
                    do {
                        toker.next();
                        this.parseArrayDecl(toker);
                    } while (toker.curr() == ',');
                    break;
                case 'local':
                    do {
                        toker.next();
                        this.parseVarDecl(toker, 'local', false);
                    } while (toker.curr() == ',');
                    break;
                case 'global':
                    do {
                        toker.next();
                        this.parseVarDecl(toker, 'global', false);
                    } while (toker.curr() == ',');
                    break;
                case '.':
                    toker.next();
                    tokens.push({
                        tokenType: 'string',
                        range: toker.range(),
                        tokenModifiers: []
                    });
                    toker.next();
                    break;
                case 'eof':
                    return;
                default:
                    if (scope != 'prog') return;
                    toker.next();
                    break;
            }
        }
    }

    static parseTypeTag(toker: BlitzToker): string {
        switch (toker.curr()) {
            case '%':
                toker.next();
                return '%';
            case '#':
                toker.next();
                return '#';
            case '$':
                toker.next();
                return '$';
            case '.':
                toker.next();
                tokens.push({
                    tokenType: 'type',
                    range: toker.range(),
                    tokenModifiers: []
                });
                return toker.next() || '';
            default:
                return '';
        }
    }

    static parseVar(toker: BlitzToker, ident?: boolean, tag?: boolean) {
        if (!ident && !tag) {
            tokens.push({
                tokenType: 'variable',
                range: toker.range(),
                tokenModifiers: []
            });
            toker.next();
            this.parseTypeTag(toker);
        }
        if (toker.curr() == '(') {
            toker.next();
            this.parseExprSeq(toker);
            toker.next();
        }

        for (; ;) {
            if (toker.curr() == '\\') {
                toker.next();
                tokens.push({
                    tokenType: 'field',
                    range: toker.range(),
                    tokenModifiers: []
                });
                toker.next();
                this.parseTypeTag(toker);
            } else if (toker.curr() == '[') {
                toker.next();
                this.parseExprSeq(toker);
                toker.next();
            } else {
                break;
            }
        }
    }

    static parseVarDecl(toker: BlitzToker, kind: 'local' | 'global' | 'param' | 'field', constant: boolean) {
        tokens.push({
            tokenType: kind == 'param' ? 'parameter' : kind == 'field' ? kind : 'variable',
            range: toker.range(),
            tokenModifiers: constant ? ['readonly'] : []
        });
        toker.next();
        this.parseTypeTag(toker);
        if (toker.curr() == '[') {
            toker.next();
            this.parseExprSeq(toker);
            toker.next();
        } else {
            if (toker.curr() == '=') {
                toker.next();
                this.parseExpr(toker);
            }
        }
    }

    static parseArrayDecl(toker: BlitzToker) {
        tokens.push({
            tokenType: this.arrayTokenType,
            range: toker.range(),
            tokenModifiers: []
        });
        toker.next();
        this.parseTypeTag(toker);
        toker.next();
        this.parseExprSeq(toker, 'parameter');
        toker.next();
    }

    static parseFuncDecl(toker: BlitzToker) {
        toker.next();
        tokens.push({
            tokenType: 'function',
            range: toker.range(),
            tokenModifiers: parsed.funcs.find(fun => fun.ident == toker.text().toLowerCase())?.deprecated === undefined ? [] : ['deprecated']
        });
        toker.next();
        this.parseTypeTag(toker);
        if (toker.next() != ')') {
            for (; ;) {
                this.parseVarDecl(toker, 'param', false);
                if (toker.curr() != ',') break;
                toker.next();
            }
        }
        toker.next();
        this.parseStmtSeq(toker, 'fun');
        toker.next();
    }

    static parseStructDecl(toker: BlitzToker) {
        toker.next();
        tokens.push({
            tokenType: 'type',
            range: toker.range(),
            tokenModifiers: []
        });
        toker.next();
        while (toker.curr() == '\n') toker.next();
        while (toker.curr() == 'field') {
            do {
                toker.next();
                this.parseVarDecl(toker, 'field', false);
            } while (toker.curr() == ',');
            while (toker.curr() == '\n') toker.next();
        }
        toker.next();
    }

    static parseIf(toker: BlitzToker) {
        this.parseExpr(toker);
        if (toker.curr() == 'then') toker.next();
        const blockIf = isTerm(toker.curr());
        this.parseStmtSeq(toker, blockIf ? 'block' : 'line');
        if (toker.curr() == 'elseif') {
            toker.next();
            this.parseIf(toker);
        } else if (toker.curr() == 'else') {
            toker.next();
            this.parseStmtSeq(toker, blockIf ? 'block' : 'line');
        }
    }

    static parseExprSeq(toker: BlitzToker, override?: string) {
        while (this.parseExpr(toker, override)) {
            if (toker.curr() != ',') break;
            toker.next();
        }
    }

    static parseExpr(toker: BlitzToker, override?: string) {
        if (toker.curr() == 'not') {
            toker.next();
            const lhs = this.parseExpr1(toker, override);
            if (!lhs) return;
        }
        return this.parseExpr1(toker, override);
    }

    static parseExpr1(toker: BlitzToker, override?: string) {
        const start = toker.range().start;
        let lhs = this.parseExpr2(toker, override);
        if (!lhs) return;
        for (; ;) {
            const c = toker.curr();
            if (c != 'and' && c != 'or' && c != 'xor') return lhs;
            toker.next();
            this.parseExpr2(toker, override);
        }
    }

    static parseExpr2(toker: BlitzToker, override?: string) {
        const start = toker.range().start;
        let lhs = this.parseExpr3(toker, override);
        if (!lhs) return;
        for (; ;) {
            const c = toker.curr();
            if (c != '<' && c != '>' && c != '=' && c != 'le' && c != 'ge' && c != 'ne') return lhs;
            toker.next();
            this.parseExpr3(toker, override);
        }
    }

    static parseExpr3(toker: BlitzToker, override?: string) {
        const start = toker.range().start;
        let lhs = this.parseExpr4(toker, override);
        if (!lhs) return;
        for (; ;) {
            const c = toker.curr();
            if (c != '+' && c != '-') return lhs;
            toker.next();
            this.parseExpr4(toker, override);
        }
    }

    static parseExpr4(toker: BlitzToker, override?: string) {
        const start = toker.range().start;
        let lhs = this.parseExpr5(toker, override);
        if (!lhs) return;
        for (; ;) {
            const c = toker.curr();
            if (c != 'shl' && c != 'shr' && c != 'sar') return lhs;
            toker.next();
            this.parseExpr5(toker, override);
        }
    }

    static parseExpr5(toker: BlitzToker, override?: string) {
        const start = toker.range().start;
        let lhs = this.parseExpr6(toker, override);
        if (!lhs) return;
        for (; ;) {
            const c = toker.curr();
            if (c != '*' && c != '/' && c != 'mod') return lhs;
            toker.next();
            this.parseExpr6(toker, override);
        }
    }

    static parseExpr6(toker: BlitzToker, override?: string) {
        let lhs = this.parseUniExpr(toker, override);
        if (!lhs) return;
        for (; ;) {
            const c = toker.curr();
            if (c != '^') return lhs;
            toker.next();
            this.parseUniExpr(toker, override);
        }
    }

    static parseUniExpr(toker: BlitzToker, override?: string) {
        const c = toker.curr();
        switch (c) {
            case 'int':
                if (toker.next() == '%') toker.next();
                break;
            case 'float':
                if (toker.next() == '#') toker.next();
                break;
            case 'str':
                if (toker.next() == '$') toker.next();
                break;
            case 'object':
                if (toker.next() == '.') toker.next();
                tokens.push({
                    tokenType: 'type',
                    range: toker.range(),
                    tokenModifiers: []
                });
                toker.next();
                this.parseUniExpr(toker, override);
                break;
            case 'handle':
            case 'before':
            case 'after':
            case '+':
            case '-':
            case 'abs':
            case 'sgn':
            case '~':
                toker.next();
                this.parseUniExpr(toker, override);
                break;
            default:
                this.parsePrimary(toker, override);
                break;
        }
        return true;
    }

    static parsePrimary(toker: BlitzToker, override?: string) {
        switch (toker.curr()) {
            case '(':
                toker.next();
                this.parseExpr(toker, override);
                if (toker.curr() == ')') toker.next();
                break;
            case 'new':
            case 'first':
            case 'last':
                toker.next();
                tokens.push({
                    tokenType: 'type',
                    range: toker.range(),
                    tokenModifiers: []
                });
                toker.next();
                break;
            case 'null':
            case 'intconst':
            case 'floatconst':
            case 'stringconst':
            case 'binconst':
            case 'hexconst':
            case 'pi':
            case 'true':
            case 'false':
                toker.next();
                break;
            case 'ident':
                const token = {
                    tokenType: override || 'variable',
                    range: toker.range(),
                    tokenModifiers: parsed.consts.find(c => c.ident == toker.text()) ? ['readonly'] : []
                };
                const ident = toker.text().toLowerCase();
                toker.next();
                this.parseTypeTag(toker);
                const isArray = parsed.arrayDecls.has(ident);
                if (toker.curr() == '(' && !isArray) {
                    token.tokenType = 'function';
                    if (parsed.funcs.find(fun => fun.ident == toker.text().toLowerCase())?.deprecated !== undefined) token.tokenModifiers.push('deprecated');
                    toker.next();
                    this.parseExprSeq(toker, override);
                    toker.next();
                } else {
                    if (isArray) token.tokenType = this.arrayTokenType;
                    this.parseVar(toker, true, true);
                }
                tokens.push(token);
                break;
        }
    }
}
