import * as vscode from 'vscode';
import { analyzed, obtainWorkingDir, parsed, userLibs } from '../context/context';
import { isInString, startOfComment } from '../util/functions';
import { getFieldFromNestedExpression } from './hoverProvider';
import { isAbsolute, join } from 'path';

export default class DefinitionProvider implements vscode.DefinitionProvider {
    provideDefinition(document: vscode.TextDocument, position: vscode.Position, token: vscode.CancellationToken): vscode.ProviderResult<vscode.Definition | vscode.LocationLink[]> {
        const wr = document.getWordRangeAtPosition(position);
        // not on whitespace
        if (!wr) return;
        if (document.lineAt(position.line).text[position.character] == ' ') return;
        const line = document.lineAt(position).text;
        // not in strings
        if (isInString(line, position.character)) {
            const lineText = document.lineAt(position.line).text.toLowerCase();
            let startOfString, endOfString;
            for (startOfString = position.character; lineText[startOfString] != '"'; startOfString--);
            if (startOfString < 7) return;
            if (lineText.substring(0, startOfString - 1).trimEnd().endsWith('include')) {
                for (endOfString = position.character; endOfString < lineText.length && lineText[endOfString] != '"'; endOfString++);
                const inpath = document.lineAt(position).text.substring(startOfString + 1, endOfString);
                return [{
                    originSelectionRange: new vscode.Range(position.line, startOfString + 1, position.line, endOfString),
                    targetRange: new vscode.Range(0, 0, 0, 0),
                    targetUri: vscode.Uri.file(isAbsolute(inpath) ? inpath : join(obtainWorkingDir(document.uri), inpath))
                }];
            } else return;
        }
        // not in comments
        if (position.character >= startOfComment(line)) return;

        let def = document.getText(wr).toLowerCase();

        // Check for field declaration
        const field = getFieldFromNestedExpression(line.substring(0, wr.start.character), def, new vscode.Location(document.uri, position));
        if (field) {
            return {
                range: field.field!.declarationRange,
                uri: field.field!.uri
            };
        }

        const constant = parsed.consts.find(constant => constant.ident == def);
        if (constant) return { uri: constant.uri, range: constant.range };

        const context = parsed.funcs.find(fun => fun.uri.path == document.uri.path && position.isAfter(fun.range.start) && position.isBefore(fun.range.end));
        if (context) {
            const local = context.locals.find(local => local.ident == def);
            if (local) return { uri: local.uri, range: local.range };
        }

        for (const arr of parsed.arrayDecls.values()) {
            if (arr.ident == def) return { uri: arr.uri, range: arr.range };
        }

        const variable = parsed.globals.concat(analyzed.locals).find(v => v.ident == def);
        if (variable) return { uri: variable.uri, range: variable.range };

        const label = parsed.labels.find(label => label.ident == def);
        if (label) return { uri: label.uri, range: label.range };

        const fun = parsed.funcs.concat(userLibs).find(fun => fun.ident == def);
        if (fun) return { uri: fun.uri, range: fun.range };

        const type = parsed.structs.find(type => type.ident == def);
        if (type) return { uri: type.uri, range: type.range };
        // TODO implement smart selection of token kind: previously matchbefore & matchafter
    }

}