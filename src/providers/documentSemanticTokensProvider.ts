import * as vscode from 'vscode';
import { blitzCtx } from '../context/context';
import { isInString, startOfComment } from '../util/functions';
import { BlitzToken, BlitzIterator, BlitzDimmedArray, BlitzFunction, BlitzType } from '../util/types';

const tokenTypes = new Map<string, number>();
const tokenModifiers = new Map<string, number>();

interface IParsedToken {
    line: number;
    startCharacter: number;
    length: number;
    tokenType: string;
    tokenModifiers: string[];
}

export const legend = (function () {
    const tokenTypesLegend = [
        'comment', 'string', 'keyword', 'number', 'operator', 'type', 'typeParameter', 'function', 'variable', 'parameter', 'property', 'field'
    ];
    tokenTypesLegend.forEach((tokenType, index) => tokenTypes.set(tokenType, index));

    const tokenModifiersLegend = [
        'declaration', 'readonly', 'modification'
    ];
    tokenModifiersLegend.forEach((tokenModifier, index) => tokenModifiers.set(tokenModifier, index));

    return new vscode.SemanticTokensLegend(tokenTypesLegend, tokenModifiersLegend);
})();

export default class BlitzSemanticTokensProvider implements vscode.DocumentSemanticTokensProvider {
    async provideDocumentSemanticTokens(document: vscode.TextDocument, token: vscode.CancellationToken): Promise<vscode.SemanticTokens> {
        const allTokens = BlitzSemanticTokensProvider._parseText(document.uri, document.getText());
        const builder = new vscode.SemanticTokensBuilder();
        allTokens.forEach((token) => {
            builder.push(token.line, token.startCharacter, token.length, this._encodeTokenType(token.tokenType), this._encodeTokenModifiers(token.tokenModifiers));
        });
        return builder.build();
    }

    private _encodeTokenType(tokenType: string): number {
        if (tokenTypes.has(tokenType)) {
            return tokenTypes.get(tokenType)!;
        } else if (tokenType === 'notInLegend') {
            return tokenTypes.size + 2;
        }
        return 0;
    }

    private _encodeTokenModifiers(strTokenModifiers: string[]): number {
        let result = 0;
        for (let i = 0; i < strTokenModifiers.length; i++) {
            const tokenModifier = strTokenModifiers[i];
            if (tokenModifiers.has(tokenModifier)) {
                result = result | (1 << tokenModifiers.get(tokenModifier)!);
            } else if (tokenModifier === 'notInLegend') {
                result = result | (1 << tokenModifiers.size + 2);
            }
        }
        return result;
    }

    static _parseText(uri: vscode.Uri, text: string): IParsedToken[] {
        const r: IParsedToken[] = [];
        const lines = text.split(/\r\n|\r|\n/);
        const tokens: BlitzToken[] = blitzCtx.flatMap(c => c.tokens);
        for (let i = 0; i < lines.length; i++) {
            const oline = lines[i].toLowerCase();
            const tline = oline.trim();
            const scpos = startOfComment(tline);
            const oscpos = startOfComment(oline);

            for (const t of tokens) {
                /*
                    I will refactor this later...				
                */
                if (t instanceof BlitzIterator && (i < t.range.start.line || i > t.endPosition.line)) continue;
                for (let st = oline.indexOf(t.lcname); st != -1 && (scpos === -1 || st < oscpos); st = oline.indexOf(t.lcname, st + 1)) {
                    // not in strings
                    if (isInString(oline, st)) continue;
                    if (t.matchBefore && !oline.substring(0, st).match(t.matchBefore)) continue;
                    if (t.matchAfter && !oline.substring(st + t.lcname.length).match(t.matchAfter)) continue;
                    if (st >= 0 && !oline[st - 1]?.match(/\w/) && !oline[st + t.lcname.length]?.match(/\w/)) {
                        r.push({
                            line: i,
                            startCharacter: st,
                            length: t.lcname.length,
                            tokenType: t instanceof BlitzDimmedArray ? 'variable' : t.type,
                            tokenModifiers: []
                        });
                    }
                }
                if (t instanceof BlitzFunction && t.uri.path == uri.path && i >= t.declarationRange.start.line && i < t.endPosition.line) t.locals.forEach((loc) => {
                    for (let st = oline.indexOf(loc.lcname); st != -1 && (scpos === -1 || st < oscpos); st = oline.indexOf(loc.lcname, st + 1)) {
                        // not in strings
                        if (isInString(oline, st)) continue;
                        if (loc.matchBefore && !oline.substring(0, st).match(loc.matchBefore)) continue;
                        if (loc.matchAfter && !oline.substring(st + loc.lcname.length).match(loc.matchAfter)) continue;
                        if (loc instanceof BlitzIterator && (i < loc.range.start.line || i > loc.endPosition.line)) continue;
                        if (st >= 0 && !oline[st - 1]?.match(/\w/) && !oline[st + loc.lcname.length]?.match(/\w/)) {
                            r.push({
                                line: i,
                                startCharacter: st,
                                length: loc.lcname.length,
                                tokenType: loc.type,
                                tokenModifiers: []
                            });
                        }
                    }
                });
                if (t instanceof BlitzType) t.fields.forEach((f) => {
                    for (let st = oline.indexOf(f.lcname); st != -1 && st < startOfComment(oline); st = oline.indexOf(f.lcname, st + 1)) {
                        // not in strings
                        if (isInString(oline, st)) continue;
                        if (f.matchBefore && !oline.substring(0, st).match(f.matchBefore)) continue;
                        if (f.matchAfter && !oline.substring(st + f.lcname.length).match(f.matchAfter)) continue;
                        if (st >= 0 && !oline[st - 1]?.match(/\w/) && !oline[st + f.lcname.length]?.match(/\w/)) {
                            r.push({
                                line: i,
                                startCharacter: st,
                                length: f.lcname.length,
                                tokenType: 'property',
                                tokenModifiers: []
                            });
                        }
                    }
                });
            }
        }
        return r;
    }

}
