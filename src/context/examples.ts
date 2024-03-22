import * as path from 'path';
import * as vscode from 'vscode';

import { readdirSync } from 'fs';
import { blitzpath } from "./context";

/**
 * Show folder contents to open .bb samples from
 * @param viewpath The current directory to search in
 */
function showSampleBrowser(viewpath: string) {
    const files = readdirSync(viewpath);
    const options = files.filter((val) => !val.includes('.') || val.endsWith('.bb'));
    if (viewpath != path.join(blitzpath, 'samples')) options.push('..');
    vscode.window.showQuickPick(options).then((chosen) => {
        if (!chosen) return;
        if (chosen == '..') viewpath = viewpath.substring(0, viewpath.lastIndexOf(path.sep));
        else viewpath = viewpath + path.sep + chosen;
        if (chosen?.endsWith('.bb')) {
            vscode.workspace.openTextDocument(vscode.Uri.parse('bbdoc:' + viewpath, true)/*vscode.Uri.file(viewpath)*/).then((document) => { vscode.window.showTextDocument(document); });
        } else {
            showSampleBrowser(viewpath);
        }
    });
}

/**
 * Open example in read-only editor of specified command, or launch example browser quickpick menu
 * @param command command to open example for
 */
export default function openExample(command?: string) {
    if (!blitzpath) {
        vscode.window.showErrorMessage('BlitzPath is not set. Please set BlitzPath as environment variable or in the extension settings.', 'Go to settings')
            .then(value => { if (value) vscode.commands.executeCommand('workbench.action.openSettings', 'blitz3d.installation.BlitzPath'); });
        return;
    }
    const cpath = path.join(blitzpath, 'help', 'commands') + path.sep;
    if (command) {
        const commands2d = readdirSync(cpath + '2d_examples');
        if (commands2d.includes(command + '.bb')) vscode.workspace.openTextDocument(vscode.Uri.parse('bbdoc:' + path.join(cpath, '2d_examples', command + '.bb'), true)).then((document) => { vscode.window.showTextDocument(document); });
        else {
            const commands3d = readdirSync(cpath + '3d_examples');
            if (commands3d.includes(command + '.bb')) vscode.workspace.openTextDocument(vscode.Uri.parse('bbdoc:' + path.join(cpath, '3d_examples', command + '.bb'), true)).then((document) => { vscode.window.showTextDocument(document); });
            else vscode.window.showWarningMessage('Could not find example for requested command.');
        }
        return;
    }
    vscode.window.showQuickPick(['2D command examples', '3D command examples', 'Samples']).then((category) => {
        switch (category) {
            case '2D command examples':
                const commands2d = readdirSync(cpath + '2d_examples');
                vscode.window.showQuickPick(commands2d).then((val) => {
                    if (val) vscode.workspace.openTextDocument(vscode.Uri.parse('bbdoc:' + path.join(cpath, '2d_examples', val), true)).then((document) => { vscode.window.showTextDocument(document); });
                });
                break;
            case '3D command examples':
                const commands3d = readdirSync(cpath + '3d_examples');
                vscode.window.showQuickPick(commands3d).then((val) => {
                    if (val) vscode.workspace.openTextDocument(vscode.Uri.parse('bbdoc:' + path.join(cpath, '3d_examples', val), true)).then((document) => { vscode.window.showTextDocument(document); });
                });
                break;
            case 'Samples':
                let viewpath = path.join(blitzpath, 'samples');
                showSampleBrowser(viewpath);
        }
    });
}
