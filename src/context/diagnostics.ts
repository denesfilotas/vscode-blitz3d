import * as vscode from 'vscode';

export let diagnosticCollection: vscode.DiagnosticCollection;
export let compilationErrors: vscode.DiagnosticCollection;

export function initializeDiagnostics() {
    diagnosticCollection = vscode.languages.createDiagnosticCollection('blitz3d');
    compilationErrors = vscode.languages.createDiagnosticCollection('blitzcc');
}