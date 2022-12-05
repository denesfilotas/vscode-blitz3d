import { readFileSync } from 'fs';
import * as vscode from 'vscode';

export default class TextDocumentContentProvider implements vscode.TextDocumentContentProvider {

    provideTextDocumentContent(uri: vscode.Uri, token: vscode.CancellationToken): vscode.ProviderResult<string> {
        return readFileSync(uri.fsPath).toString();
    }

}
