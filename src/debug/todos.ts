import * as vscode from 'vscode';
import { diagnosticCollection } from '../context/diagnostics';

export default function updateTodos(document: vscode.TextDocument) {

    if (document.languageId != 'blitz3d') {
        diagnosticCollection.delete(document.uri);
        return;
    }

    let diagnostics = diagnosticCollection.get(document.uri)?.filter(d => d.code !== "TODO") ?? [];

    const todotype = vscode.workspace.getConfiguration('blitz3d.editor').get<string>('UseTodos');
    if (todotype != 'Off') {
        for (let i = 0; i < document.lineCount; i++) {
            const line = document.lineAt(i).text;
            if (line.toLowerCase().indexOf(';;todo ') !== -1) diagnostics.push({
                message: 'TODO: ' + line.trim().substring(line.toLowerCase().indexOf(';;todo ') + 7),
                range: new vscode.Range(i, line.toLowerCase().indexOf(';;todo '), i, line.length),
                severity: todotype == 'Information' ? vscode.DiagnosticSeverity.Information : vscode.DiagnosticSeverity.Warning,
                code: "TODO"
            });
        }
    }

    diagnosticCollection.set(document.uri, diagnostics);
}