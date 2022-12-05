import * as vscode from 'vscode';
import * as path from 'path';

export default class BlitzConfigurationProvider implements vscode.DebugConfigurationProvider {
    resolveDebugConfiguration(folder: vscode.WorkspaceFolder | undefined, config: vscode.DebugConfiguration, token?: vscode.CancellationToken): vscode.ProviderResult<vscode.DebugConfiguration> {
        if (!config.type && !config.request && !config.name) {
            const editor = vscode.window.activeTextEditor;
            if (editor && editor.document.languageId === 'blitz3d') {
                config.type = 'blitz3d';
                config.name = 'Debug Blitz3D program';
                config.request = 'launch';
                config.bbfile = '${file}';
            }
        }
        return config;
    }
    resolveDebugConfigurationWithSubstitutedVariables(folder: vscode.WorkspaceFolder, config: vscode.DebugConfiguration, token?: vscode.CancellationToken): vscode.ProviderResult<vscode.DebugConfiguration> {
        if (!config.bbfile || config.bbfile.substring(config.bbfile.length - 2) != 'bb') {
            return vscode.window.showErrorMessage('No file to debug.').then(_ => undefined);
        }
        if (!path.isAbsolute(config.bbfile)) {
            //console.log('BB file is not absolute, finding ' + config.bbfile + ' in workspace');
            config.bbfile = vscode.workspace.workspaceFolders?.[0].uri.fsPath + path.sep + config.bbfile;
        }
        return config;
    }
}
