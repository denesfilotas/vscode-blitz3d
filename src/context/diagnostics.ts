import * as vscode from 'vscode';

export let syntaxErrors: vscode.DiagnosticCollection;
export let semanticErrors: vscode.DiagnosticCollection;
export let compilationErrors: vscode.DiagnosticCollection;
export let userLibErrors: vscode.DiagnosticCollection;

export function initializeDiagnostics(context: vscode.ExtensionContext) {
    syntaxErrors = vscode.languages.createDiagnosticCollection('b3d-parse');
    semanticErrors = vscode.languages.createDiagnosticCollection('b3d-analyze');
    compilationErrors = vscode.languages.createDiagnosticCollection('blitzcc');
    userLibErrors = vscode.languages.createDiagnosticCollection('b3d-decls');

    context.subscriptions.push(syntaxErrors);
    context.subscriptions.push(semanticErrors);
    context.subscriptions.push(compilationErrors);
    context.subscriptions.push(userLibErrors);
}