import * as vscode from 'vscode';
import { analyzed, parsed, userLibs } from '../context/context';
import { isInString, startOfComment } from '../util/functions';
import { getFieldFromNestedExpression } from './hoverProvider';

export default class DefinitionProvider implements vscode.DefinitionProvider {
    provideDefinition(document: vscode.TextDocument, position: vscode.Position, token: vscode.CancellationToken): vscode.ProviderResult<vscode.Definition | vscode.LocationLink[]> {
        const wr = document.getWordRangeAtPosition(position);
        // not on whitespace
        if (!wr) return;
        if (document.lineAt(position.line).text[position.character] == ' ') return;
        const line = document.lineAt(position).text;
        // not in strings
        if (isInString(line, position.character)) return;
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