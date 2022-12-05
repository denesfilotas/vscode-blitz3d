import * as vscode from 'vscode';
import * as path from 'path';
import { blitzCtx } from '../context/context';
import { isInString, removeType, startOfComment } from '../util/functions';
import { BlitzFunction, BlitzToken, BlitzType, BlitzVariable } from '../util/types';
import { stubs } from '../context/stubs';

export function getFieldFromNestedExpression(exp: string, name: string, tokens: BlitzToken[], location: vscode.Location): { field: BlitzVariable, parent: BlitzType; } | undefined {
    const parents: string[] = [];
    const tline = exp.trim().replace(/\s*\\\s*/g, '\\');
    if (tline.length > 1) {
        const x = tline.length - 1;
        let fend = x;
        if (tline[x] == '\\') {
            for (let i = x - 1; i >= 0; i--) {
                if (tline[i] == ']') {
                    i = tline.substring(0, i).lastIndexOf('[');
                    fend = i;
                    continue;
                }
                if (tline[i] == ')') {
                    i = tline.substring(0, i).lastIndexOf('(');
                    fend = i;
                    continue;
                }
                if (tline[i] == '\\') {
                    parents.unshift(removeType(tline.substring(i + 1, fend)));
                    fend = i;
                    continue;
                }
                if (!tline[i].match(/\w/)) {
                    parents.unshift(removeType(tline.substring(i + 1, fend)));
                    break;
                }
                if (i == 0) {
                    parents.unshift(removeType(tline.substring(0, fend)));
                }
            }
        }
    }
    if (parents.length > 0) {
        let curr: BlitzType | undefined;
        tlu: for (const t of tokens
            .concat((<Array<BlitzFunction>>tokens.filter(
                t => t instanceof BlitzFunction
                    && location.uri.path == t.uri.path
                    && location.range.start.isAfter(t.declarationRange.start)
                    && location.range.end.isBefore(t.range.end)))
                .flatMap(t => t.locals))) {
            if (t.lcname == parents[0].toLowerCase()) {
                let type = '';
                if (t instanceof BlitzFunction) type = t.returnType.split('[')[0].split('(')[0];
                else if (t instanceof BlitzVariable) type = t.dataType.split('[')[0].split('(')[0];
                for (const t of tokens.filter(t => t instanceof BlitzType)) {
                    if (t.lcname == type.toLowerCase()) {
                        curr = <BlitzType>t;
                        break tlu;
                    }
                }
            }
        }
        ps: for (let i = 1; curr && i < parents.length; i++) {
            const parent = parents[i].toLowerCase();
            for (const f of curr.fields) {
                if (f.lcname == parent) {
                    const type = f.dataType.split('[')[0].split('(')[0];
                    for (const t of tokens.filter(t => t instanceof BlitzType)) {
                        if (t.lcname == type.toLowerCase()) {
                            curr = <BlitzType>t;
                            continue ps;
                        }
                    }
                }
            }
        }
        if (curr) {
            for (const f of curr.fields) {
                if (f.lcname == name) {
                    return {
                        field: f,
                        parent: curr
                    };
                }
            }
        }
    }
}

export default class BlitzHoverProvider implements vscode.HoverProvider {
    public provideHover(document: vscode.TextDocument, position: vscode.Position, token: vscode.CancellationToken): vscode.ProviderResult<vscode.Hover> {
        const desc = new vscode.MarkdownString();
        const iwr = document.getWordRangeAtPosition(position);
        // not on whitespace
        if (!iwr) return;
        let wr: vscode.Range = iwr;
        // not on number literals
        if (document.getText(wr).match(/^\-?[0-9\.]+$/)) return;
        if (document.lineAt(position.line).text[position.character] == ' ') return;
        const line = document.lineAt(position).text;
        // not in strings
        if (isInString(line, position.character)) return;
        // not in comments
        if (position.character >= startOfComment(line)) return;

        let def = document.getText(wr).toLowerCase();
        if (def == 'end' || def == 'else') {
            // add following word on certain tokens
            const nwr = document.getWordRangeAtPosition(wr.end.translate(0, 2));
            if (nwr) {
                const m = document.getText(nwr).toLowerCase().match(/if|function|type|select/)?.toString();
                if (def == 'end' && m) {
                    def += ' ' + m;
                    wr = new vscode.Range(wr.start, nwr.end);
                } else if (def == 'else' && m == 'if') {
                    def = 'else if';
                    wr = new vscode.Range(wr.start, nwr.end);
                }
            }
        } else if (wr.start.character > 2 && def.match(/if|function|type|select/)) {
            const nwr = document.getWordRangeAtPosition(wr.start.translate(0, -2));
            // only one space is allowed, no need for dynamic whitespace checking
            if (nwr) {
                const nw = document.getText(nwr).toLowerCase();
                if (nw == 'end') {
                    def = nw + ' ' + def;
                    wr = new vscode.Range(nwr.start, wr.end);
                } else if (nw == 'else' && def == 'if') {
                    def = 'else if';
                    wr = new vscode.Range(nwr.start, wr.end);
                }
            }
        }
        let dl = 'Defined in ' + document.fileName.substring(document.fileName.lastIndexOf(path.sep) + 1);
        const example = new vscode.MarkdownString();
        example.isTrusted = true;
        for (const stub of stubs) {
            if (stub.name.toLowerCase() == def) {
                def = '```\n' + stub.declaration + '\n```';
                desc.appendMarkdown('\n#### Parameters\n');
                stub.parameters.forEach((p) => {
                    const fsp = (p.trimStart() + ' ').indexOf(' ');
                    desc.appendMarkdown('\n `' + p.trimStart().substring(0, fsp) + '` ' + p.trim().substring(fsp) + '  \n');

                });
                desc.appendMarkdown('\n\n#### Description\n');
                stub.description.forEach((descLine) => {
                    desc.appendMarkdown('\n' + descLine);
                });
                if (stub.example.length == 0) break;
                // desc.appendMarkdown('\n#### Example\n');
                //desc.appendCodeblock(stub.example.replace('\r\n', '  \n'));
                example.appendMarkdown(`[Open example](command:extension.blitz3d.openExample?${encodeURIComponent(JSON.stringify(stub.name))})`);
                dl = '';
                break;
            }
        }
        const tokens: BlitzToken[] = blitzCtx.flatMap(c => c.tokens);

        // check if hovered over a field
        const parents: string[] = [];
        const field = getFieldFromNestedExpression(line.substring(0, wr.start.character), def, tokens, new vscode.Location(document.uri, position));
        if (field) {
            return {
                contents: [
                    '```\n' + field.field.declaration + '\n```',
                    new vscode.MarkdownString(field.field.description),
                    'Defined in Type ' + field.parent.oname
                ],
                range: wr
            };
        }

        let newdef = '', priornewdef = '';
        for (const t of tokens) {
            if (t instanceof BlitzFunction && t.uri.path == document.uri.path && position.isAfter(t.declarationRange.start) && position.isBefore(t.endPosition)) {
                for (const loc of t.locals) {
                    if (def == loc.lcname && !((loc.matchBefore && !line.substring(0, wr.start.character).match(loc.matchBefore)) || (loc.matchAfter && !line.substring(wr.end.character).match(loc.matchAfter)))) {
                        priornewdef = '```\n' + loc.declaration + '\n```';
                        if (loc.description) desc.appendMarkdown(loc.description);
                        dl = 'Defined in Function ' + t.oname;
                    }
                };
            }
            if (def == t.lcname) {
                if (t.matchBefore && !line.substring(0, wr.start.character).match(t.matchBefore)) continue;
                if (t.matchAfter && !line.substring(wr.end.character).match(t.matchAfter)) continue;
                newdef = '```\n' + t.declaration + '\n```';
                if (t instanceof BlitzFunction && t.stub /*&& '#$%. ('.indexOf(t.declaration.charAt(t.name.length + 9)) >= 0*/) {
                    t.stub.description.forEach((descLine) => {
                        desc.appendMarkdown(descLine);
                    });
                    if (t.stub.parameters.length > 0) desc.appendMarkdown('\n#### Parameters\n');
                    t.stub.parameters.forEach((p) => {
                        desc.appendMarkdown(p + '  \n');
                    });
                }
                if (t.uri != document.uri) dl = 'Defined in ' + t.uri.path.substring(t.uri.path.lastIndexOf('/') + 1);
                if (t instanceof BlitzVariable && t.description) desc.appendText(t.description);
                if (!(t instanceof BlitzVariable)) break; // Variables can be dimmed
            }
        }
        if (priornewdef.length > 0) def = priornewdef;
        else if (newdef.length > 0) def = newdef;
        // const hover = new vscode.MarkdownString();
        // hover.supportHtml = true;
        // hover.appendMarkdown(desc.join('  \n'));
        return {
            contents: [
                def,
                desc,
                example,
                dl
            ],
            range: wr
        };
    }
}