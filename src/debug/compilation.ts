import * as vscode from 'vscode';
import * as cp from 'child_process';
import * as path from 'path';
import { blitzpath } from '../context/context';
import { compilationErrors, diagnosticCollection } from '../context/diagnostics';

export default function compile(document: vscode.TextDocument) {

    compilationErrors.clear();
    if (document.languageId != 'blitz3d') return;

    const compiletype = vscode.workspace.getConfiguration('blitz3d.compilation').get<string>('AutoCompilation');
    if (compiletype == 'None') return;

    let uri: vscode.Uri;
    if (compiletype == 'Open file') {
        uri = document.uri;
    } else {
        const folder = vscode.workspace.workspaceFolders?.[0];
        const config = vscode.workspace.getConfiguration('launch', folder?.uri);
        try {
            const bbfile = config.get<any[]>("configurations")?.[0].bbfile;
            const bbpath = path.isAbsolute(bbfile) ? bbfile : path.join(folder?.uri.path.substring(1) ?? '.', bbfile);
            uri = vscode.Uri.file(bbpath);
        } catch {
            uri = document.uri;
        }
    }

    blitzcc(uri);

    if (compiletype == 'Both' && uri.path != document.uri.path) blitzcc(document.uri);
}

function blitzcc(uri: vscode.Uri) {

    // run compiler with env got from config
    const env = process.env;
    if (blitzpath.length > 0) {
        env['PATH'] += path.delimiter + path.join(blitzpath, 'bin');
        env['BLITZPATH'] = blitzpath;
    }
    cp.exec(`blitzcc -c "${uri.path.substring(1)}"`, env, (err, sout, serr) => {
        if (err) {
            if (serr.length > 0
                || sout.trim() == "Can't find blitzpath environment variable"
                || sout.trim() == "Unable to open linker.dll") {
                let diagnostics = diagnosticCollection.get(uri)?.filter(d => d.code !== "blitzcc") ?? [];
                diagnostics.push({
                    message: 'BlitzPath is configured incorrectly. Compilation is unavailable.',
                    range: new vscode.Range(0, 0, 0, 0),
                    severity: vscode.DiagnosticSeverity.Information,
                    code: "blitzcc"
                });
                diagnosticCollection.set(uri, diagnostics);
            }
            const lines = sout.toLowerCase().split(/\r\n|\r|\n/);
            const diagnostics = [];
            for (const l of lines) {
                if (l.indexOf(':') >= 0 && !l.startsWith('compiling')) {
                    const s = l.split(':');
                    const msg = s[s.length - 1];
                    const file = l.split('"')[1];
                    const erruri = vscode.Uri.file(file);
                    const lineno = parseInt(s[s.length - 3]) - 1;
                    const charno = parseInt(s[s.length - 2]);
                    const startPos = new vscode.Position(lineno, charno);
                    diagnostics.push({
                        message: msg,
                        range: new vscode.Range(startPos, startPos),
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