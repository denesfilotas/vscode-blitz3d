import * as vscode from 'vscode';

type Toke = {
    type: string,
    from: number,
    to: number;
};

function isspace(ch: string): boolean {
    if (!ch || ch.length != 1) return false;
    return ' \f\n\r\t\v'.includes(ch);
}

function isdigit(ch: string): boolean {
    if (!ch || ch.length != 1) return false;
    return '0123456789'.includes(ch);
}

function isxdigit(ch: string): boolean {
    if (!ch || ch.length != 1) return false;
    return '0123456789abcdefABCDEF'.includes(ch);
}

function isalpha(ch: string): boolean {
    if (!ch || ch.length != 1) return false;
    return 'abcdefghijklmnopqrstuvwxyz'.includes(ch.toLowerCase());
}

function isalnum(ch: string): boolean {
    if (!ch || ch.length != 1) return false;
    return '0123456789_abcdefghijklmnopqrstuvwxyz'.includes(ch.toLowerCase());
}

const keywords = ['dim', 'goto', 'gosub', 'return', 'exit', 'if', 'then', 'else', 'endif', 'end if', 'elseif', 'else if', 'while', 'wend', 'for', 'to', 'step', 'next', 'function', 'end function', 'type', 'end type', 'each', 'local', 'global', 'field', 'const', 'select', 'case', 'default', 'end select', 'repeat', 'until', 'forever', 'data', 'read', 'restore', 'abs', 'sgn', 'mod', 'pi', 'true', 'false', 'int', 'float', 'str', 'include', 'new', 'delete', 'first', 'last', 'insert', 'before', 'after', 'null', 'object', 'handle', 'and', 'or', 'xor', 'not', 'shl', 'shr', 'sar'];

export class BlitzToker {
    private input: string[];
    private tokes: Toke[] = [];
    private currToke: number = -1;

    private currRow: number = -1;
    private line: string = '\n';

    constructor(input: string) {
        this.input = input.split(/\r\n|\r|\n/);
        this.nextLine();
    }

    range(): vscode.Range {
        return new vscode.Range(this.currRow, this.tokes[this.currToke].from, this.currRow, this.tokes[this.currToke].to);
    }

    curr(): string {
        return this.tokes[this.currToke].type;
    }

    text(): string {
        return this.line.substring(this.tokes[this.currToke].from, this.tokes[this.currToke].to);
    }

    lookAhead(n: number): string | undefined {
        return this.tokes[this.currToke + n]?.type;
    }

    next(): string | undefined {
        if (++this.currToke >= this.tokes.length) this.nextLine();
        return this.curr();
    }

    private nextLine() {
        this.currRow++;
        this.currToke = 0;
        this.tokes = [];
        if (this.currRow >= this.input.length) {
            this.line = '\n';
            this.tokes.push({
                type: 'eof',
                from: 0,
                to: 1
            });
            return;
        }
        this.line = this.input[this.currRow] + '\n';

        for (let k = 0; k < this.line.length;) {
            const c = this.line[k], from = k;
            if (c == '\n') {
                this.tokes.push({ type: c, from: from, to: ++k });
                continue;
            }
            if (isspace(c)) {
                ++k;
                continue;
            }
            if (c == ';') {
                this.tokes.push({ type: '\n', from: k, to: this.line.length - 1 });
                break;
            }
            if (c == '.' && isdigit(this.line[k + 1])) {
                for (k += 2; isdigit(this.line[k]); ++k);
                this.tokes.push({ type: 'floatconst', from: from, to: k });
                continue;

            }
            if (isdigit(c)) {
                for (++k; isdigit(this.line[k]); ++k);
                if (this.line[k] == '.') {
                    for (++k; isdigit(this.line[k]); ++k);
                    this.tokes.push({ type: 'floatconst', from: from, to: k });
                    continue;
                }
                this.tokes.push({ type: 'intconst', from: from, to: k });
                continue;
            }
            if (c == '%' && (this.line[k + 1] == '0' || this.line[k + 1] == '1')) {
                for (k += 2; this.line[k] == '0' || this.line[k] == '1'; ++k);
                this.tokes.push({ type: 'binconst', from: from, to: k });
                continue;
            }
            if (c == '$' && isxdigit(this.line[k + 1])) {
                for (k += 2; isxdigit(this.line[k]); ++k);
                this.tokes.push({ type: 'hexconst', from: from, to: k });
                continue;
            }
            if (isalpha(c)) {
                for (++k; isalnum(this.line[k]); ++k);
                let ident = this.line.substring(from, k).toLowerCase();

                if (this.line[k] == ' ' && isalpha(this.line[k + 1])) {
                    let t = k;
                    for (t += 2; isalnum(this.line[t]); ++t);
                    const s = this.line.substring(from, t).toLowerCase();
                    if (keywords.includes(s)) {
                        k = t;
                        ident = s;
                    }
                }

                if (keywords.includes(ident)) {
                    this.tokes.push({ type: ident.replace(' ', ''), from: from, to: k });
                    continue;
                }

                this.tokes.push({ type: 'ident', from: from, to: k });
                continue;
            }

            if (c == '"') {
                for (++k; this.line[k] != '"' && this.line[k] != '\n'; ++k);
                if (this.line[k] == '"') ++k;
                this.tokes.push({ type: 'stringconst', from: from, to: k });
                continue;
            }

            const n = this.line[k + 1];
            if ((c == '<' && n == '>') || (c == '>' && n == '<')) {
                k += 2;
                this.tokes.push({ type: 'ne', from: from, to: k });
                continue;
            }
            if ((c == '<' && n == '=') || (c == '=' && n == '<')) {
                k += 2;
                this.tokes.push({ type: 'le', from: from, to: k });
                continue;
            }
            if ((c == '>' && n == '=') || (c == '=' && n == '>')) {
                k += 2;
                this.tokes.push({ type: 'ge', from: from, to: k });
                continue;
            }
            this.tokes.push({ type: c, from: from, to: ++k });
        }
    }

}

export class DeclToker {
    private input: string;
    private _text: string = '';
    private _curr: string = '';
    private line: number = 0;
    private linestart: number = 0;
    private position: number = -1;

    constructor(text: string) {
        this.input = text;
    }

    range(): vscode.Range {
        return new vscode.Range(this.line, this.position - this.linestart - this._text.length, this.line, this.position - this.linestart);
    }

    curr(): string {
        return this._curr;
    }

    text(): string {
        return this._text;
    }

    next(): string {
        this._text = '';
        for (; ;) {
            while (this.position < this.input.length && isspace(this.input[this.position + 1])) ++this.position;
            if (this.position >= this.input.length) return this._curr = 'eof';
            if (this.input[this.position] == '\n') { this.line++; this.linestart = this.position; }
            if (this.input[++this.position] != ';') break;
            while (this.position < this.input.length && this.input[++this.position] != '\n');
            this.line++;
            this.linestart = this.position;
        }
        if (isalpha(this.input[this.position])) {
            this._text += this.input[this.position];
            while (isalnum(this.input[this.position + 1])) this._text += this.input[++this.position];
            return this._curr = 'ident';
        }
        if (this.input[this.position] == '"') {
            while (this.input[this.position + 1] != '"') this._text += this.input[++this.position];
            this.position++;
            return this._curr = 'string';
        }
        if (this.position >= this.input.length) return this._curr = 'eof';
        return this._curr = this.input[this.position];
    }
}