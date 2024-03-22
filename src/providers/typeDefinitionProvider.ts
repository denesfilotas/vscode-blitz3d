import * as vscode from 'vscode';
import { analyzed, parsed } from '../context/context';
import { Type } from '../context/types';
import { isInString, removeType, startOfComment } from '../util/functions';

export function getType(exp: string, location: vscode.Location): Type | undefined {
    const parents: string[] = [];
    const tline = exp.trim().replace(/\s*\\\s*/g, '\\');
    if (tline.length > 1) {
        const x = tline.length - 1;
        let fend = x + 1;
        for (let i = x; i >= 0; i--) {
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
                if (i != x) {
                    parents.unshift(removeType(tline.substring(i + 1, fend)));
                }
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
    if (parents.length > 0) {
        let curr: Type | undefined;
        for (const t of parsed.consts.concat(parsed.globals, analyzed.locals, parsed.funcs.find(fun => fun.uri.path == location.uri.path && fun.range.contains(location.range))?.locals ?? [], [...parsed.arrayDecls.values()])) {
            if (t.ident == parents[0].toLowerCase()) {
                const type = t.tag;
                curr = parsed.structs.find(struct => struct.ident == type);
                if (['function', 'constant', 'global', 'local'].includes(t.kind)) break; // if it is not explicitly declared with keywords, it might be declared later
            }
        }
        for (let i = 1; curr && i < parents.length; i++) {
            const parent = parents[i].toLowerCase();
            const field = curr.fields.find(f => f.ident == parent);
            if (!field || '%#$'.includes(field.tag)) return; // TODO diag
            const type = parsed.structs.find(t => t.ident == field.tag);
            if (type) curr = type;
        }
        return curr;
    }
}

export class TypeDefinitionProvider implements vscode.TypeDefinitionProvider {
    provideTypeDefinition(document: vscode.TextDocument, position: vscode.Position, token: vscode.CancellationToken): vscode.ProviderResult<vscode.Definition | vscode.LocationLink[]> {
        const wr = document.getWordRangeAtPosition(position);
        // not on whitespace
        if (!wr) return;
        // not on number literals
        if (document.getText(wr).match(/^\-?[0-9\.]+$/)) return;

        const line = document.lineAt(position).text;
        // not in strings
        if (isInString(line, position.character)) return;
        // not in comments
        if (position.character >= startOfComment(line)) return;

        const type = getType(line.substring(0, wr.end.character), new vscode.Location(document.uri, wr));
        if (!type) return;
        return new vscode.Location(type.uri, type.range);
    }
}