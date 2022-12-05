import * as vscode from 'vscode';
import { blitzCtx } from '../context/context';
import { stubs } from '../context/stubs';
import { startOfComment, isInString, isBlitzKeyword } from '../util/functions';
import { BlitzVariable, BlitzFunction, BlitzType, BlitzDimmedArray } from '../util/types';

export default class CompletionItemProvider implements vscode.CompletionItemProvider {
    private _typeToKind(type: string): vscode.CompletionItemKind | undefined {
        switch (type) {
            case 'function':
                return vscode.CompletionItemKind.Function;
            case 'type':
                return vscode.CompletionItemKind.Class;
            case 'variable':
                return vscode.CompletionItemKind.Variable;
            default:
                return;
        }
    }
    provideCompletionItems(document: vscode.TextDocument, position: vscode.Position, token: vscode.CancellationToken, context: vscode.CompletionContext): vscode.ProviderResult<vscode.CompletionItem[] | vscode.CompletionList<vscode.CompletionItem>> {
        const tokens = blitzCtx.flatMap(c => c.tokens);
        const r: vscode.CompletionItem[] = [];

        // not in comments
        if (position.character >= startOfComment(document.lineAt(position).text)) return;
        // not in strings
        if (isInString(document.lineAt(position).text, position.character)) return;

        let lastPos = position.character == 0 ? position : position.translate(0, -1);
        while (lastPos.character > 0 && document.getText(new vscode.Range(lastPos, lastPos.translate(0, 1))).match(/\s|\w/)) {
            lastPos = lastPos.translate(0, -1);
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
            if (wr) {
                const par = document.getText(wr).toLowerCase();
                // look up type of parent
                let typename = '';
                for (const t of tokens) {
                    if (t instanceof BlitzVariable && t.lcname == par) {
                        typename = t.dataType;
                        break;
                    } else if (t instanceof BlitzFunction && position.isAfter(t.declarationRange.start) && position.isBefore(t.endPosition)) {
                        for (const loc of t.locals) {
                            if (loc.lcname == par) {
                                typename = loc.dataType;
                            }
                        };
                    } else if (t instanceof BlitzType /*&& document.getText(new vscode.Range(pwr?.start.translate(0, -1), pwr?.start))*/) {
                        for (const f of t.fields) {
                            if (f.lcname == par) {
                                typename = f.dataType;
                            }
                        }
                    }
                }
                typename = typename.toLowerCase();
                for (const t of tokens) {
                    if (t instanceof BlitzType && t.lcname == typename) {
                        for (const f of t.fields) {
                            const ci = new vscode.CompletionItem({ label: f.oname, detail: (f.dataType.charAt(0).match(/\w/) ? '.' : '') + f.dataType, description: f.description }, vscode.CompletionItemKind.Field);
                            ci.detail = f.description ? f.description : '';
                            r.push(ci);
                        }
                    }
                }
                return new vscode.CompletionList(r);
            } else return;
        }

        // Types
        if ((context.triggerCharacter == '.' && position.character >= 2
            && document.lineAt(position.line).text[position.character - 2].match(/\w/))
            || document.getText(wr).toLowerCase().match(/^new$|^each$|^first$|^last$/)) {
            for (const t of tokens) {
                if (t.type == 'type') r.push(new vscode.CompletionItem(t.oname, this._typeToKind(t.type)));
            }
            return new vscode.CompletionList(r);
        }

        if (pwr && document.getText(pwr).match(/^(function|type|local|global|const|dim|field)$/i)) return;

        // general IntelliSense
        const useSnippets = vscode.workspace.getConfiguration('blitz3d.editor').get<boolean>('InsertParameterSnippets');
        for (const t of tokens) {
            if (t instanceof BlitzType) continue;
            let ci = new vscode.CompletionItem({ label: t.oname }, this._typeToKind(t.type));
            if (t instanceof BlitzFunction) {
                ci.label = {
                    label: t.oname,
                    detail: (t.returnType.charAt(0).match(/\w/) ? '.' : '') + t.returnType + '()',
                    description: t.stub?.description.join(' ')
                };
                const snip = new vscode.SnippetString(t.oname + '(');
                if (useSnippets) {
                    let fst = true;
                    for (const param of t.locals.filter(f => f.storageType == 'param')) {
                        if (!fst) snip.appendText(', ');
                        snip.appendPlaceholder(param.oname);
                        fst = false;
                    }
                } else {
                    snip.appendTabstop(0);
                }
                snip.appendText(')');
                ci.insertText = snip;
                ci.command = {
                    title: 'Trigger Parameter Hints',
                    command: 'editor.action.triggerParameterHints'
                };
                ci.documentation = new vscode.MarkdownString(t.stub?.description.join('  \n'));
                if (position.isAfter(t.declarationRange.start) && position.isBefore(t.endPosition) && document.uri.path == t.uri.path) {
                    t.locals.forEach(loc => {
                        r.push(new vscode.CompletionItem({ label: loc.oname, detail: (loc.dataType.charAt(0).match(/\w/) ? '.' : '') + loc.dataType, description: '(local) ' + (loc.description ? loc.description : '') }, vscode.CompletionItemKind.Variable));
                    });
                }
            } else if (t instanceof BlitzVariable) {
                ci.label = {
                    label: t.oname,
                    detail: (t.dataType.charAt(0).match(/\w/) ? '.' : '') + t.dataType,
                    description: t.description
                };
                ci.documentation = t.description;
            } else if (t instanceof BlitzDimmedArray) {
                ci.label = {
                    label: t.oname,
                    detail: (t.dataType.charAt(0).match(/\w/) ? '.' : '') + t.dataType,
                    description: 'Dimmed array'
                };
                ci.insertText = new vscode.SnippetString(t.oname + '($0)');
                ci.command = {
                    title: 'Trigger Parameter Hints',
                    command: 'editor.action.triggerParameterHints'
                };
            }
            r.push(ci);
        }

        // Builtin functions
        const usebrackets = vscode.workspace.getConfiguration('blitz3d.editor').get<boolean>('UseBracketsEverywhere');
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
            if (!isKw) ci.command = {
                title: 'Trigger Parameter Hints',
                command: 'editor.action.triggerParameterHints'
            };
            r.push(ci);
        }
        return new vscode.CompletionList(r);
    }
}
