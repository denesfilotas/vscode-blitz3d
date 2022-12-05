import * as vscode from 'vscode';
import { blitzCtx } from '../context/context';
import { removeType, extractType } from '../util/functions';
import { BlitzFunction, BlitzVariable, BlitzDimmedArray, BlitzType } from '../util/types';

export default class DocumentSymbolProvider implements vscode.DocumentSymbolProvider {
    private static _typeToKind(type: string): vscode.SymbolKind {
        switch (type) {
            case 'function':
                return vscode.SymbolKind.Function;
            case 'type':
                return vscode.SymbolKind.Class;
            case 'label':
                return vscode.SymbolKind.String;
            case 'variable':
                return vscode.SymbolKind.Variable;
            case 'field':
                return vscode.SymbolKind.Field;
            case 'label':
                return vscode.SymbolKind.String;
            case 'array':
                return vscode.SymbolKind.Array;
            default:
                return vscode.SymbolKind.Module;
        }
    }
    provideDocumentSymbols(document: vscode.TextDocument, token: vscode.CancellationToken): vscode.ProviderResult<vscode.DocumentSymbol[]> {
        const classicSymbols = vscode.workspace.getConfiguration('blitz3d.outline').get<boolean>('ClassicOutlineSymbols');
        if (classicSymbols) {
            const stPos = new vscode.Position(0, 0);
            const endPos = document.lineAt(document.lineCount - 1).range.end;
            const nulRng = new vscode.Range(stPos, endPos);
            let funcs: vscode.DocumentSymbol[] = [];
            let types: vscode.DocumentSymbol[] = [];
            let labels: vscode.DocumentSymbol[] = [];
            for (let i = 0; i < document.lineCount; i++) {
                const line = document.lineAt(i);
                const s = line.text.toLowerCase().trim();
                const fn = s.match(/(?<=^function\b).+(?=\()/)?.toString();
                if (fn) {
                    funcs.push(new vscode.DocumentSymbol(removeType(fn), extractType(fn), vscode.SymbolKind.Function, line.range, line.range));
                }
                if (s.match(/^end function/)) {
                    const lf = funcs.pop();
                    if (lf) {
                        lf.range = new vscode.Range(lf.range.start, line.range.end);
                        funcs.push(lf);
                    }
                }
                const tn = s.match(/(?<=^type\b).+$/)?.toString();
                if (tn) {
                    types.push(new vscode.DocumentSymbol(tn, 'type', vscode.SymbolKind.Class, line.range, line.range));
                }
                if (s.match(/^end type/)) {
                    const lt = types.pop();
                    if (lt) {
                        lt.range = new vscode.Range(lt.range.start, line.range.end);
                        types.push(lt);
                    }
                }
                if (s.length > 1 && s.startsWith('.')) {
                    labels.push(new vscode.DocumentSymbol(s.substring(1), 'label', vscode.SymbolKind.String, line.range, line.range));
                }
            }
            const funcscontainer = new vscode.DocumentSymbol('functions', '', vscode.SymbolKind.Module, nulRng, nulRng);
            funcscontainer.children = funcs;
            const typescontainer = new vscode.DocumentSymbol('types', '', vscode.SymbolKind.Module, nulRng, nulRng);
            typescontainer.children = types;
            const labelscontainer = new vscode.DocumentSymbol('labels', '', vscode.SymbolKind.Module, nulRng, nulRng);
            labelscontainer.children = labels;
            return [funcscontainer, typescontainer, labelscontainer];
            //return funcs.concat(types).concat(labels);
        }
        const includeParams = vscode.workspace.getConfiguration('blitz3d.outline').get<boolean>('ParametersInOutline');
        const tokens = blitzCtx.flatMap(c => c.tokens);
        const symbols: vscode.DocumentSymbol[] = [];
        for (const t of tokens) {
            if (t.uri.path == document.uri.path) {
                let dataType = t.type;
                if (t instanceof BlitzFunction) dataType = t.returnType;
                if (t instanceof BlitzVariable) dataType = t.dataType;
                if (t instanceof BlitzDimmedArray) dataType = t.dataType + '(' + t.dimension + ')';
                let s = new vscode.DocumentSymbol(t.oname, dataType, dataType.endsWith('[]') ? vscode.SymbolKind.Array : DocumentSymbolProvider._typeToKind(t.type), t.range, t.declarationRange);
                if (t instanceof BlitzFunction) {
                    for (const loc of t.locals) {
                        if (includeParams || loc.type != 'parameter') s.children.push(new vscode.DocumentSymbol(loc.oname, loc.dataType, DocumentSymbolProvider._typeToKind(loc.type), loc.range, loc.declarationRange));
                    }
                }
                if (t instanceof BlitzType) {
                    for (const f of t.fields) {
                        s.children.push(new vscode.DocumentSymbol(f.oname, f.dataType, DocumentSymbolProvider._typeToKind(f.type), f.range, f.declarationRange));
                    }
                }
                symbols.push(s);
            }
        }
        return symbols;
    }

}