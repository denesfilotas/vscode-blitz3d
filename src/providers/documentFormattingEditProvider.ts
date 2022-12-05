import * as vscode from 'vscode';
import { startOfComment, isInString } from '../util/functions';

export default class DocumentFormattingEditProvider implements vscode.DocumentFormattingEditProvider {
    provideDocumentFormattingEdits(document: vscode.TextDocument, options: vscode.FormattingOptions, token: vscode.CancellationToken): vscode.ProviderResult<vscode.TextEdit[]> {
        // determine indentation characters
        if (!vscode.window.activeTextEditor) return;
        const ret: vscode.TextEdit[] = [];
        let ind = '';
        let target = 0;
        if (vscode.window.activeTextEditor?.options.insertSpaces) {
            const tabSize = <number>vscode.window.activeTextEditor?.options.tabSize;
            ind = ' '.repeat(tabSize);
        } else ind = '\t';
        for (let i = 0; i < document.lineCount; i++) {
            const line = document.lineAt(i);
            const lineText = line.text.toLowerCase();
            //clear empty lines
            if (lineText.trim().length == 0) {
                if (lineText.length > 0) ret.push(vscode.TextEdit.delete(line.range));
                continue;
            }
            //indentate
            if (target > 0 && lineText.match(/^\s*(w?end|endif|until|forever|next|case|default|else(if)?)\b/)) target--;
            if (target > 0 && lineText.match(/^\s*end select\b/)) target--;
            ret.push(vscode.TextEdit.replace(new vscode.Range(i, 0, i, lineText.length - lineText.trimStart().length), ind.repeat(target)));
            if (lineText.match(/^\s*(function|type|if|else(if)?|select|case|default|repeat|while|for)\b/)) target++;
            if (lineText.match(/^\s*select\b/)) target++;
            if (lineText.trimStart().startsWith('if ') && lineText.indexOf('then') > 0 && startOfComment(lineText.split('then')[1].trim()) > 1) target--;
            // split on colons
            if (!lineText.startsWith('if ')) {
                for (let c = lineText.indexOf(':'); c != -1; c = lineText.indexOf(':', c + 1)) {
                    if (!isInString(lineText, c) && c < startOfComment(lineText)) {
                        ret.push(vscode.TextEdit.replace(new vscode.Range(i, c, i, c + 1 + lineText.substring(c).length - lineText.substring(c).trimStart().length), '\n' + ind.repeat(target)));
                    }
                }
            }
            // remove whitespace from end of line
            if (lineText.match(/\s$/)) {
                ret.push(vscode.TextEdit.delete(new vscode.Range(i, lineText.trimEnd().length, i, line.range.end.character)));
            }
        }
        return ret;
    }
}