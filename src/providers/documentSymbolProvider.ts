import * as vscode from 'vscode';
import { analyzed, parsed, userLibs } from '../context/context';

export default class DocumentSymbolProvider implements vscode.DocumentSymbolProvider {

    provideDocumentSymbols(document: vscode.TextDocument, token: vscode.CancellationToken): vscode.ProviderResult<vscode.DocumentSymbol[]> {
        const classicSymbols = vscode.workspace.getConfiguration('blitz3d.outline').get<boolean>('ClassicOutlineSymbols');
        if (classicSymbols) {
            const stPos = new vscode.Position(0, 0);
            const endPos = document.lineAt(document.lineCount - 1).range.end;
            const nulRng = new vscode.Range(stPos, endPos);
            const funcs: vscode.DocumentSymbol[] = [];
            for (const fun of parsed.funcs.concat(userLibs)) {
                if (fun.uri.path != document.uri.path) continue;
                funcs.push(new vscode.DocumentSymbol(fun.name, fun.tag, vscode.SymbolKind.Function, fun.range, fun.declarationRange));
            }
            const types: vscode.DocumentSymbol[] = [];
            for (const type of parsed.structs) {
                if (type.uri.path != document.uri.path) continue;
                types.push(new vscode.DocumentSymbol(type.name, '', vscode.SymbolKind.Struct, type.range, type.declarationRange));
            }
            const labels: vscode.DocumentSymbol[] = [];
            for (const label of parsed.labels) {
                if (label.uri.path != document.uri.path) continue;
                labels.push(new vscode.DocumentSymbol(label.name, '', vscode.SymbolKind.String, label.range, label.declarationRange));
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
        const symbols: vscode.DocumentSymbol[] = [];
        for (const fun of parsed.funcs.concat(userLibs)) {
            if (fun.uri.path != document.uri.path) continue;
            const symbol = new vscode.DocumentSymbol(fun.name, fun.tag, vscode.SymbolKind.Function, fun.range, fun.declarationRange);
            for (const local of fun.locals) {
                if (!includeParams && local.kind == 'param') continue;
                symbol.children.push(new vscode.DocumentSymbol(local.name, local.tag, vscode.SymbolKind.Variable, local.range, local.declarationRange));
            }
            symbols.push(symbol);
        }
        for (const constant of parsed.consts) {
            if (constant.uri.path != document.uri.path) continue;
            symbols.push(new vscode.DocumentSymbol(constant.name, constant.tag, vscode.SymbolKind.Constant, constant.range, constant.declarationRange));
        }
        for (const type of parsed.structs) {
            if (type.uri.path != document.uri.path) continue;
            const symbol = new vscode.DocumentSymbol(type.name, '', vscode.SymbolKind.Struct, type.range, type.declarationRange);
            for (const field of type.fields) {
                symbol.children.push(new vscode.DocumentSymbol(field.name, field.tag, vscode.SymbolKind.Field, field.range, field.declarationRange));
            }
            symbols.push(symbol);
        }
        for (const global of parsed.globals.concat(analyzed.locals)) {
            if (global.uri.path != document.uri.path) continue;
            symbols.push(new vscode.DocumentSymbol(global.name, global.tag, vscode.SymbolKind.Variable, global.range, global.declarationRange));
        }
        for (const label of parsed.labels) {
            if (label.uri.path != document.uri.path) continue;
            symbols.push(new vscode.DocumentSymbol(label.name, '', vscode.SymbolKind.String, label.range, label.declarationRange));
        }
        return symbols;
    }

}