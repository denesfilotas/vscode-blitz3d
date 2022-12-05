import * as vscode from 'vscode';
import { blitzCtx } from '../context/context';
import { isInString, startOfComment } from '../util/functions';
import { BlitzFunction, BlitzVariable } from '../util/types';
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
        const tokens = blitzCtx.flatMap(c => c.tokens);

        // Check for field declaration
        const field = getFieldFromNestedExpression(line.substring(0, wr.start.character), def, tokens, new vscode.Location(document.uri, position));
        if (field) {
            return {
                range: field.field.declarationRange,
                uri: field.field.uri
            };
        }

        let range: vscode.Range | undefined = undefined;
        let uri: vscode.Uri | undefined = undefined;
        for (const t of tokens) {
            if (def == t.lcname) {
                if (t.matchBefore && !line.substring(0, wr.start.character).match(t.matchBefore)) continue;
                if (t.matchAfter && !line.substring(wr.end.character).match(t.matchAfter)) continue;
                if (t instanceof BlitzFunction) range = new vscode.Range(t.declarationRange.start, t.endPosition);
                else range = t.declarationRange;
                uri = t.uri;
                if (!(t instanceof BlitzVariable)) break; // Variables can be dimmed
            }
            if (t instanceof BlitzFunction && t.uri.path == document.uri.path && position.isAfter(t.declarationRange.start) && position.isBefore(t.endPosition)) {
                t.locals.forEach((loc) => {
                    if (def == loc.lcname && !((loc.matchBefore && !line.substring(0, wr.start.character).match(loc.matchBefore)) || (loc.matchAfter && !line.substring(wr.end.character).match(loc.matchAfter)))) {
                        def = '```\n' + loc.declaration + '\n```';
                        range = loc.declarationRange;
                        uri = loc.uri;
                    }
                });
            }
        }
        if (uri && range) return { uri, range };
        return;
    }

}