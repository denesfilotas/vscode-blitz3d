import * as vscode from 'vscode';
import { analyzed, parsed, userLibs } from '../context/context';
import { stubs } from '../context/stubs';
import { isBlitzKeyword, isInString, startOfComment } from '../util/functions';
import { getType } from './typeDefinitionProvider';

export default class CompletionItemProvider implements vscode.CompletionItemProvider {

    provideCompletionItems(document: vscode.TextDocument, position: vscode.Position, token: vscode.CancellationToken, context: vscode.CompletionContext): vscode.ProviderResult<vscode.CompletionItem[] | vscode.CompletionList<vscode.CompletionItem>> {
        const r: vscode.CompletionItem[] = [];

        // not in comments
        if (position.character >= startOfComment(document.lineAt(position).text)) return;
        // not in strings
        if (isInString(document.lineAt(position).text, position.character)) return;

        let lastPos = position.character == 0 ? position : position.translate(0, -1);
        if (lastPos.character > 0 && document.getText(new vscode.Range(lastPos, lastPos.translate(0, 1))).match(/\s/)) {
            while (lastPos.character > 0 && document.getText(new vscode.Range(lastPos, lastPos.translate(0, 1))).match(/\s/)) {
                lastPos = lastPos.translate(0, -1);
            }
        } else if (lastPos.character > 0 && document.getText(new vscode.Range(lastPos, lastPos.translate(0, 1))).match(/\w/)) {
            while (lastPos.character > 0 && document.getText(new vscode.Range(lastPos, lastPos.translate(0, 1))).match(/\w/)) {
                lastPos = lastPos.translate(0, -1);
            }
        }
        const lastChar = document.getText(new vscode.Range(lastPos, lastPos.translate(0, 1)));
        while (lastPos.character > 0 && !document.getText(new vscode.Range(lastPos, lastPos.translate(0, 1))).match(/\w/)) {
            lastPos = lastPos.translate(0, -1);
        }
        const wr = document.getWordRangeAtPosition(lastPos);
        if (wr && wr.start.character > 0) lastPos = wr.start.translate(0, -1);
        while (lastPos.character > 0 && !document.getText(new vscode.Range(lastPos, lastPos.translate(0, 1))).match(/\w/)) {
            lastPos = lastPos.translate(0, -1);
        }
        const pwr = document.getWordRangeAtPosition(lastPos);
        // Fields of type
        if (context.triggerCharacter == '\\' || lastChar == '\\') {
            if (!wr) return;
            const exp = document.lineAt(position).text.substring(0, wr.end.character);
            const type = getType(exp, new vscode.Location(document.uri, position));
            if (!type) return;
            for (const field of type.fields) {
                r.push({
                    label: {
                        label: field.name,
                        description: field.tag
                    },
                    documentation: field.description,
                    kind: vscode.CompletionItemKind.Field,
                });
            }
            return new vscode.CompletionList(r);
        }

        // Types
        if ((context.triggerCharacter == '.' && position.character >= 2
            && document.lineAt(position.line).text[position.character - 2].match(/\w/))
            || ['new', 'each', 'first', 'last'].includes(document.getText(wr).toLowerCase())) {
            for (const t of parsed.structs) {
                r.push(new vscode.CompletionItem(t.name, vscode.CompletionItemKind.Struct));
            }
            return new vscode.CompletionList(r);
        }

        // Labels
        if (['goto', 'gosub', 'restore'].includes(document.getText(wr).toLowerCase())) {
            for (const label of parsed.labels) {
                r.push(new vscode.CompletionItem(label.name, vscode.CompletionItemKind.Text));
            }
            return new vscode.CompletionList(r);
        }

        if (pwr && document.getText(pwr).match(/^(function|type|local|global|const|dim|field)$/i)) return;

        // general IntelliSense
        const useSnippets = vscode.workspace.getConfiguration('blitz3d.editor').get<boolean>('InsertParameterSnippets');
        const usebrackets = vscode.workspace.getConfiguration('blitz3d.editor').get<boolean>('UseBracketsEverywhere');
        for (const fun of parsed.funcs.concat(userLibs)) {
            const snip = new vscode.SnippetString(fun.name + (fun.tag || usebrackets ? '(' : ' '));
            if (useSnippets) {
                let fst = true;
                for (const param of fun.params) {
                    if (!fst) snip.appendText(', ');
                    snip.appendPlaceholder(param.name);
                    fst = false;
                }
            } else {
                snip.appendTabstop(0);
            }
            if (fun.tag || usebrackets) snip.appendText(')');
            r.push({
                label: {
                    label: fun.name,
                    detail: ('%#$'.includes(fun.tag) ? '' : '.') + fun.tag,
                    description: `${fun.kind == 'userlib' ? 'library' : 'user-defined'} function`
                },
                kind: vscode.CompletionItemKind.Function,
                insertText: snip,
                command: {
                    title: 'Trigger Parameter Hints',
                    command: 'editor.action.triggerParameterHints'
                },
                documentation: new vscode.MarkdownString(fun.description + (fun.deprecated ? `\n\nDeprecated: ${fun.deprecated}` : '')),
                tags: fun.deprecated !== undefined ? [vscode.CompletionItemTag.Deprecated] : []
            });

            // local variables
            if (fun.uri.path == document.uri.path && fun.range.contains(position)) {
                for (const local of fun.locals) {
                    r.push(new vscode.CompletionItem({
                        label: local.name,
                        detail: ('%#$'.includes(local.tag) ? '' : '.') + local.tag,
                        description: local.kind == 'param' ? 'function parameter' : 'local variable'
                    }, vscode.CompletionItemKind.Variable));
                }
            }
        }

        for (const variable of parsed.globals.concat(analyzed.locals)) {
            r.push({
                label: {
                    label: variable.name,
                    detail: ('%#$'.includes(variable.tag) ? '' : '.') + variable.tag,
                    description: 'global variable'
                },
                kind: vscode.CompletionItemKind.Variable,
                documentation: new vscode.MarkdownString(variable.description)
            });
        }

        for (const variable of parsed.consts) {
            r.push({
                label: {
                    label: variable.name,
                    detail: ('%#$'.includes(variable.tag) ? '' : '.') + variable.tag,
                    description: 'constant'
                },
                kind: vscode.CompletionItemKind.Constant,
                documentation: new vscode.MarkdownString(variable.description)
            });
        }

        for (const array of parsed.arrayDecls.values()) {
            const snip = new vscode.SnippetString(array.name + '(');
            if (useSnippets) {
                for (let i = 0; i < array.dimension; i++) {
                    if (i > 0) snip.appendText(', ');
                    snip.appendPlaceholder('index' + i);
                }
            } else {
                snip.appendTabstop(0);
            }
            snip.appendText(')');
            r.push({
                label: {
                    label: array.name,
                    detail: ('%#$'.includes(array.tag) ? '' : '.') + array.tag,
                    description: 'dimmed array'
                },
                kind: vscode.CompletionItemKind.Variable,
                insertText: snip,
                command: {
                    title: 'Trigger Parameter Hints',
                    command: 'editor.action.triggerParameterHints'
                },
            });
        }

        // Builtin functions
        for (const stub of stubs) {
            const isKw = isBlitzKeyword(stub.name.split(' ')[0]);
            let ci = new vscode.CompletionItem({ label: stub.name, detail: stub.declaration.substring(8).includes('(') ? '()' : '' }, isKw ? vscode.CompletionItemKind.Keyword : vscode.CompletionItemKind.Function);
            ci.documentation = new vscode.MarkdownString(stub.description.join('  \n'));
            if (useSnippets) {
                ci.insertText = stub.snippet;
            } else {
                if ((usebrackets && !isKw) || stub.declaration.substring(9).includes('(')) ci.insertText = new vscode.SnippetString(stub.name + '($0)');
                else ci.insertText = stub.name + ' ';
            }
            if (stub.name == 'Dim') ci.insertText = new vscode.SnippetString('Dim ${1:array_name}(${0:maxindex0...})');
            if (!isKw && stub.parameters.length > 0 && !stub.parameters[0].toLowerCase().includes('none')) {
                ci.command = {
                    title: 'Trigger Parameter Hints',
                    command: 'editor.action.triggerParameterHints'
                };
            }
            r.push(ci);
        }
        return new vscode.CompletionList(r);
    }
}
