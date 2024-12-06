import * as cp from 'child_process';
import * as path from 'path';
import * as vscode from 'vscode';
import { blitzCmd, blitzpath } from '../context/context';
import { compilationErrors } from '../context/diagnostics';

export default function compile(document: vscode.TextDocument) {

    compilationErrors.clear();

    const compiletype = vscode.workspace.getConfiguration('blitz3d.compilation').get<string>('AutoCompilation');
    if (compiletype == 'None') return;

    if (compiletype == 'Open file' || compiletype == 'Both') {
        if (document.languageId != 'blitz3d') return;
        blitzcc(document.uri);
    }
    if (compiletype == 'Launch file' || compiletype == 'Both') {
        const folders = vscode.workspace.workspaceFolders;
        if (folders) {
            for (const folder of folders) {
                try {
                    const config = vscode.workspace.getConfiguration('launch', folder.uri);
                    const bbfile = config.get<any[]>("configurations")?.[0]?.bbfile ?? document.fileName;
                    const bbpath = path.isAbsolute(bbfile) ? bbfile : path.join(folder.uri.path.substring(process.platform === 'win32' ? 1 : 0) ?? '.', bbfile);
                    const uri = vscode.Uri.file(bbpath);
                    if (compiletype == 'Launch file' || uri.path != document.uri.path) blitzcc(uri);
                } catch (e) {
                    vscode.window.showErrorMessage('Error occured during compilation', 'Show error')
                        .then(resp => { if (resp) vscode.window.showErrorMessage('Error: ' + e); });
                }
            }
        }
    }
}

function blitzcc(uri: vscode.Uri) {

    // run compiler with env got from config
    const binpath = path.join(blitzpath, 'bin');
    const env = process.env;
    if (blitzpath.length > 0) env['BLITZPATH'] = blitzpath;
    cp.exec(`${blitzCmd} -c "${uri.path.substring(process.platform === 'win32' ? 1 : 0)}"`, {env}, (err, sout, serr) => {
        if (err) {
            if (serr.length > 0
                || sout.trim() == "Can't find blitzpath environment variable"
                || sout.trim() == "Unable to open linker.dll") {
                let diagnostics = compilationErrors.get(uri)?.filter(d => d.code !== "blitzcc") ?? [];
                diagnostics.push({
                    message: 'BlitzPath is configured incorrectly. Compilation is unavailable.',
                    range: new vscode.Range(0, 0, 0, 0),
                    severity: vscode.DiagnosticSeverity.Information,
                    code: "blitzcc"
                });
                compilationErrors.set(uri, diagnostics);
            }
            const lines = sout.toLowerCase().split(/\r\n|\r|\n/);
            const diagnostics = [];
            for (const l of lines) {
                if (l.indexOf(':') >= 0 && !l.startsWith('compiling')) {
                    const s = l.split(':');
                    const msg = s[s.length - 1];
                    const file = l.split('"')[1];
                    const erruri = vscode.Uri.file(file);
                    const startLine = parseInt(s[s.length - 5]) - 1;
                    const startChar = parseInt(s[s.length - 4]);
                    const endLine = parseInt(s[s.length - 3]) - 1;
                    const endChar = parseInt(s[s.length - 2]);
                    diagnostics.push({
                        message: msg,
                        range: new vscode.Range(startLine, startChar, endLine, endChar),
                        severity: vscode.DiagnosticSeverity.Error,
                        code: "blitzcc"
                    });
                    compilationErrors.set(erruri, diagnostics);
                }
            }
        }
    });
}


export function showErrorOnCompile(stdout: string, stderr: string) {
    if (stderr.length > 0) {
        vscode.window.showErrorMessage('Your path does not contain the blitzcc command. Please set BlitzPath in the extension settings correctly.', 'Go to settings', 'View error').then(val => {
            switch (val) {
                case 'View error':
                    vscode.window.showErrorMessage('Your path does not contain the blitzcc command. Please set BlitzPath in the extension settings correctly.', {
                        detail: 'Error: ' + stderr,
                        modal: true
                    });
                    break;
                case 'Go to settings':
                    vscode.commands.executeCommand('workbench.action.openSettings', 'blitz3d.installation.BlitzPath');
                    break;
            }
        });
    } else {
        let msg = '';
        switch (stdout.trim()) {
            case "Can't find blitzpath environment variable":
                msg = 'BlitzPath is missing from your environment variables. Please set BlitzPath in the extension settings correctly.';
                break;
            case "Unable to open linker.dll":
                msg = 'BlitzPath is configured incorrectly. Please set BlitzPath in the extension settings correctly.';
                break;
            default:
                vscode.window.showErrorMessage('Failed to compile file: ' + stdout.substring(stdout.lastIndexOf(':') + 1), 'View output').then(val => {
                    if (val) vscode.window.showErrorMessage('Failed to compile file', {
                        detail: 'Output: ' + stdout,
                        modal: true
                    });
                });
                return;
        }
        vscode.window.showErrorMessage(msg, 'Go to settings').then(val => {
            if (val) vscode.commands.executeCommand('workbench.action.openSettings', 'blitz3d.installation.BlitzPath');
        });
    }
}