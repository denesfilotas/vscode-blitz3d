import * as vscode from 'vscode';
import { blitzCtx } from '../context/context';
import { stubs } from '../context/stubs';
import { startOfComment, isInString, removeType } from '../util/functions';
import { BlitzDimmedArray, BlitzFunction } from '../util/types';

export default class SignatureHelpProvider implements vscode.SignatureHelpProvider {

    static startPosition: vscode.Position | undefined;

    provideSignatureHelp(document: vscode.TextDocument, position: vscode.Position, token: vscode.CancellationToken, context: vscode.SignatureHelpContext): vscode.ProviderResult<vscode.SignatureHelp> {
        let ret = new vscode.SignatureHelp();
        const tokens = blitzCtx.flatMap(c => c.tokens);
        let line = document.lineAt(position).text;
        if (position.character >= startOfComment(line)) return;
        if (line.trimStart().toLowerCase().startsWith('function')) return;
        const wordend = document.getWordRangeAtPosition(position)?.end.character;
        const endc = wordend ? wordend : position.character;
        line = line.substring(0, endc).trim().toLowerCase();
        let wend = line.length;
        let pc = 0;
        let ps = [0];
        const usebrackets = vscode.workspace.getConfiguration('blitz3d.editor').get<boolean>('UseBracketsEverywhere');
        chars: for (let i = line.length - 1; i >= 0; i--) {
            const c = line.charAt(i);
            if (c == '"' || isInString(line, i)) continue;
            if ((!c.match(/\w/) || i == 0)) {
                const word = line.substring(i + (i == 0 ? 0 : 1), wend);
                if (pc < 0)
                    for (const t of tokens) {
                        if (t instanceof BlitzDimmedArray && t.lcname == word && !document.lineAt(position).text.toLowerCase().startsWith('dim')) {
                            let sigInf = new vscode.SignatureInformation('(element) ' + t.oname + '(index0');
                            sigInf.parameters.push(new vscode.ParameterInformation('index0'));
                            for (let i = 1; i < t.dimension; i++) {
                                sigInf.label += ', index' + i;
                                sigInf.parameters.push(new vscode.ParameterInformation('index' + i));
                            }
                            sigInf.label += ')';
                            ret.signatures.push(sigInf);
                            ret.activeParameter = ps[0];
                            break chars;
                        }
                        if (!(t instanceof BlitzFunction)) continue;
                        if (t.lcname == word) {
                            let sigInf = new vscode.SignatureInformation(t.declaration);
                            for (const l of t.locals) if (l.storageType == 'param') {
                                sigInf.parameters.push(new vscode.ParameterInformation(l.oname + (l.dataType == '(%)' ? '' : ('#$%'.indexOf(l.dataType) == -1 ? '.' : '') + l.dataType), l.description));
                            }
                            sigInf.documentation = t.stub?.description.concat(t.stub.parameters).join('  \n');
                            ret.signatures.push(sigInf);
                            ret.activeParameter = ps[0];
                            break chars;
                        }
                    }
                if (word == 'dim') {
                    const nameStart = line.indexOf(' ', i + 2);
                    const nameEnd = line.indexOf('(', nameStart + 1);
                    const arrname = nameEnd == -1 ? 'array_name' : line.substring(nameStart, nameEnd).trim();
                    let sigInf = new vscode.SignatureInformation('(builtin) Dim ' + arrname + '(');
                    if (nameEnd == -1) sigInf.parameters.push(new vscode.ParameterInformation(arrname));
                    for (let i = 0; i < ps[0] + 1; i++) {
                        sigInf.label += 'index' + i + ', ';
                        sigInf.parameters.push(new vscode.ParameterInformation('index' + i));
                    }
                    sigInf.label += '...)';
                    ret.signatures.push(sigInf);
                    ret.activeParameter = ps[0];
                    break chars;
                }
                for (const stub of stubs) {
                    if (stub.name.toLowerCase() == word) {
                        const params: string[] = [];
                        const dec = stub.declaration.substring(10).replace(/,?\[/, '').replace(']', '');
                        const op = dec.indexOf('(') == -1 ? dec.indexOf(' ') : dec.indexOf('(');
                        const cp = dec.indexOf(')') == -1 ? dec.length : dec.indexOf(')');
                        const pars = dec.substring(op + 1, cp).split(',');
                        for (const p of pars) params.push(p.split('=')[0].trim());
                        if ((!usebrackets && stub.declaration.indexOf('(', 2) == -1 && ret.activeParameter < params.length) || pc < 0) {
                            let sigInf = new vscode.SignatureInformation(stub.declaration);
                            for (let i = 0; i < params.length; i++) sigInf.parameters.push(new vscode.ParameterInformation(removeType(params[i]), stub.parameters[i]));
                            sigInf.documentation = new vscode.MarkdownString('#### Function description  \n' + stub.description.join('  \n'));
                            ret.signatures.push(sigInf);
                            ret.activeParameter = ps[0];
                            break chars;
                        } else continue chars;
                    }
                }
                wend = i;
            }
            if (c == ',') ps[0]++;
            if (c == ')') {
                pc++;
                ps.unshift(0);
            }
            if (c == '(') {
                pc--;
                if (ps.length > 1) ps.shift();
            }
        }
        return ret;
    }
}