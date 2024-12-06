import * as vscode from 'vscode';
import { analyzed, parsed, userLibs } from '../context/context';
import { stubs } from '../context/stubs';
import * as bb from '../context/types';
import { isBuiltinBlitzFunction, isInString, prettyName, startOfComment } from '../util/functions';
import { getType } from './typeDefinitionProvider';

export function getFieldFromNestedExpression(exp: string, name: string, location: vscode.Location): { field: bb.Variable, parent: bb.Type; } | undefined {
    const parent = getType(exp, location);
    if (!parent) return;
    const field = parent.fields.find(f => f.ident == name);
    if (!field) return;
    return {
        field: field,
        parent: parent
    };
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
        const example = new vscode.MarkdownString();
        example.isTrusted = true;

        const fun = parsed.funcs.concat(userLibs).find(fun => fun.ident == def);
        if (fun) {
            def = '```\n' + `Function ${prettyName(fun.name, fun.tag)}(${fun.params.map(param => prettyName(param.name, param.tag) + (param.value != undefined ? ' = ' + param.value : '')).join(', ')})` + '\n```';
            if (fun.description) desc.appendMarkdown(fun.description);
            if (fun.params.length > 0) {
                desc.appendMarkdown('\n#### Parameters\n');
                for (const param of fun.params) {
                    desc.appendMarkdown(`\`${param.name}\` ${param.value != undefined ? '(optional)' : ''} ${param.description ?? ''}  \n`);
                }
            }
            if (fun.returns) desc.appendMarkdown(`\n#### Returns\n${fun.returns}\n`);
            if (fun.authors?.length) {
                desc.appendMarkdown('\n#### Author' + (fun.authors.length > 1 ? 's\n' : '\n'));
                for (const author of fun.authors) {
                    desc.appendMarkdown(` - ${author}  \n`);
                }
            }
            if (fun.since) desc.appendMarkdown(`\n#### Since\n${fun.since}\n`);
            if (fun.deprecated !== undefined) desc.appendMarkdown(`\n#### Deprecated\n${fun.deprecated}\n`);
            return {
                contents: [
                    def,
                    desc,
                    example,
                    'Defined in ' + fun.uri.path.substring(fun.uri.path.lastIndexOf('/') + 1)
                ],
                range: wr
            };
        }

        const stub = stubs.find(s => s.name.toLowerCase() == def);
        if (stub) {
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
            if (stub.example) example.appendMarkdown(`[Open example](command:extension.blitz3d.openExample?${encodeURIComponent(JSON.stringify(stub.name))})`);
            return {
                contents: [
                    def,
                    desc,
                    example
                ],
                range: wr
            };
        } else if (isBuiltinBlitzFunction(def)) {
            def = '(from library) ```' + def + '```';
            desc.appendText('No description is available');
            return {
                contents: [
                    def,
                    desc
                ],
                range: wr
            };
        }

        // check if hovered over a field
        const field = getFieldFromNestedExpression(line.substring(0, wr.start.character), def, new vscode.Location(document.uri, position));
        if (field) {
            return {
                contents: [
                    '```\nField ' + prettyName(field.field!.name, field.field!.tag) + '\n```',
                    new vscode.MarkdownString(field.field!.description),
                    'Defined in Type `' + field.parent.name + '`'
                ],
                range: wr
            };
        }

        // field declaration in place
        const word = document.getText(wr).toLowerCase();
        if (word != 'field') {
            for (const type of parsed.structs) {
                if (type.uri.path != document.uri.path) continue;
                if (!type.range.contains(position)) continue;
                const field = type.fields.find(field => field.ident == word);
                if (!field) break;
                return {
                    contents: [
                        '```\nField ' + prettyName(field.name, field.tag) + '\n```',
                        new vscode.MarkdownString(field.description),
                        'Defined in Type `' + type.name + '`'
                    ],
                    range: wr
                };
            }
        }

        const constant = parsed.consts.find(constant => constant.ident == def);
        if (constant) return {
            contents: [
                '```\n' + (constant.value ? `Const ${prettyName(constant.name, constant.tag)} = ${constant.value}` : 'Const ' + prettyName(constant.name, constant.tag)) + '\n```',
                new vscode.MarkdownString(constant.description),
                'Defined in ' + constant.uri.path.substring(constant.uri.path.lastIndexOf('/') + 1)
            ],
            range: wr
        };

        const context = analyzed.funcs.find(fun => fun.uri.path == document.uri.path && position.isAfter(fun.range.start) && position.isBefore(fun.range.end));
        if (context) {
            const local = context.locals.find(local => local.ident == def);
            if (local) return {
                contents: [
                    '```\n' + (local.kind == 'param' ? '(param) ' : 'Local ') + prettyName(local.name, local.tag) + '\n```',
                    new vscode.MarkdownString(local.description),
                    'Defined in Function `' + context.name + '`'
                ],
                range: wr
            };
        }

        for (const arr of parsed.arrayDecls.values()) {
            if (arr.ident == def) {
                return {
                    contents: [
                        '```\nDim ' + prettyName(arr.name, arr.tag) + '()\n```',
                        new vscode.MarkdownString(arr.description),
                        'Defined in ' + arr.uri.path.substring(arr.uri.path.lastIndexOf('/') + 1)
                    ],
                    range: wr
                };
            }
        }

        const global = parsed.globals.find(v => v.ident == def);
        if (global) return {
            contents: [
                '```\nGlobal ' + prettyName(global.name, global.tag) + '\n```',
                new vscode.MarkdownString(global.description),
                'Defined in ' + global.uri.path.substring(global.uri.path.lastIndexOf('/') + 1)
            ],
            range: wr
        };

        const variable = analyzed.locals.find(v => v.ident == def);
        if (variable) return {
            contents: [
                '```\nLocal ' + prettyName(variable.name, variable.tag) + '\n```',
                new vscode.MarkdownString(variable.description),
                'Defined in ' + variable.uri.path.substring(variable.uri.path.lastIndexOf('/') + 1)
            ],
            range: wr
        };

        const label = parsed.labels.find(label => label.ident == def);
        if (label) return {
            contents: [
                '```\n(label) .' + label.name + '\n```',
                new vscode.MarkdownString(label.description),
                'Defined in ' + label.uri.path.substring(label.uri.path.lastIndexOf('/') + 1)
            ],
            range: wr
        };

        const type = parsed.structs.find(type => type.ident == def);
        if (type) {
            def = '```\nType ' + type.name + '\n```';
            if (type.description) desc.appendMarkdown(type.description);
            if (type.authors?.length) {
                desc.appendMarkdown('\n#### Author' + (type.authors.length > 1 ? 's\n' : '\n'));
                for (const author of type.authors) {
                    desc.appendMarkdown(` - ${author}  \n`);
                }
            }
            if (type.since) desc.appendMarkdown(`\n#### Since\n${type.since}\n`);
            return {
                contents: [
                    def,
                    desc,
                    example,
                    'Defined in ' + type.uri.path.substring(type.uri.path.lastIndexOf('/') + 1)
                ],
                range: wr
            };
        }
        return;
    }
}