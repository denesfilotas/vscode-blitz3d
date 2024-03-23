import * as vscode from 'vscode';

export class BlitzStub {
    name: string = '';
    declaration: string = '';
    parameters: string[] = [];
    description: string[] = [];
    author: string[] = [];
    return: string = '';
    since: string = '';
    example: string = '';
    snippet?: vscode.SnippetString;
}
