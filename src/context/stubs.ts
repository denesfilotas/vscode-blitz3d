import { createWriteStream, existsSync, readdirSync, readFileSync } from 'fs';
import * as path from 'path';
import * as vscode from 'vscode';
import { isBlitzKeyword, removeType } from '../util/functions';
import { BlitzStub } from "../util/types";
import { blitzpath } from './context';

export let stubs: BlitzStub[];
export let stubpath: string;

export function updateStubPath(updatedPath: string) {
    stubpath = updatedPath;
    if (existsSync(stubpath)) {
        let stubdoc = readFileSync(stubpath);
        stubs = loadDefaultStubs(stubdoc);
    } else {
        vscode.window.showInformationMessage('Blitz3D builtin keywords are not stored. Generate stubs from your Blitz3D installation.', 'Generate stubs')
            .then(resp => { if (resp) generateStubs(); });
    }
}

/**
 * Read the blitz help documents to obtain builtin keywords, and save them in the stubs.bb file
 */
export function generateStubs() {
    const cpath = path.join(blitzpath, 'help', 'commands');
    let stubs: BlitzStub[] = [];
    let ws = createWriteStream(stubpath);
    const files2d = readdirSync(path.join(cpath, '2d_commands'));
    files2d.forEach((fileName) => {
        const file = readFileSync(path.join(cpath, '2d_commands', fileName));
        let stub = generateStubFromDoc(file);
        if (fileName != 'template.htm') stubs.push(stub);
    });
    const files3d = readdirSync(path.join(cpath, '3d_commands'));
    files3d.forEach((fileName) => {
        const file = readFileSync(path.join(cpath, '3d_commands', fileName));
        let stub = generateStubFromDoc(file);
        if (fileName != 'template.htm') stubs.push(stub);
    });
    stubs.forEach((stub) => {
        stub.description.forEach((desc) => {
            ws.write(';; ' + desc + '\n');
        });
        stub.parameters.forEach((param) => {
            ws.write(';;param ' + param + '\n');
        });
        ws.write(stub.declaration + '\n');
        ws.write(stub.example);
        ws.write('end function\n');
    });
    ws.close();
    vscode.window.showInformationMessage('Stubs generated from installation folder. Reload window for changes to take effect', 'Reload')
        .then((val) => {
            if (val) {
                vscode.commands.executeCommand('workbench.action.reloadWindow');
            }
        });
}

enum StubGenState {
    declaration,
    parameters,
    description,
    example,
    done
}

function generateStubFromDoc(document: Buffer): BlitzStub {
    let declaration: string | undefined = '';
    let name: string = '';
    let parameters: string[] = [];
    let description: string[] = [];
    let example = '';
    let state = StubGenState.declaration;
    for (const line of document.toString().split(/\r\n|\r|\n/)) {
        const str = line.trim();
        if ((str.startsWith('<h1>') || str.startsWith('<p><span class="Command">')) && !declaration) {
            declaration = 'function ';
            const m1 = str.match(/(?<=\<h1\>).+(?=\<\/h1\>)/)?.toString();
            const m2 = str.match(/(?<=&nbsp;&nbsp;).+(?=&nbsp;&nbsp;)/)?.toString();
            if (m1) {
                declaration += m1;
            } else if (m2) {
                declaration += m2;
            }
            name = declaration.substring(9);
            name = name.substring(0, name.indexOf('(') == -1 ? name.length : name.indexOf('(')).trim();
            name = name.substring(0, name.indexOf(' ') == -1 ? name.length : name.indexOf(' ')).trim();
        }
        else if (str.startsWith('<td>')) {
            if (parameters.length === 0) {
                state = StubGenState.parameters;
            } else if (description.length === 0) {
                state = StubGenState.description;
            } else if (!example) {
                state = StubGenState.example;
            }
        } else if (str.startsWith('</table>') && state == StubGenState.example) {
            state = StubGenState.done;
        } else if (str.match(/\b(br|table|td|tr|h1|body)\b/)) {
            continue;
        } else {
            if (str.trim().length === 0) continue;
            switch (state) {
                case StubGenState.parameters:
                    parameters.push(str);
                    break;
                case StubGenState.description:
                    description.push(str);
                    break;
                case StubGenState.example:
                    //if (str.split('"').length % 2 == 0) example += str + '"\r\n'; else example += str + '\r\n';
                    // ^- might be config later
                    example += str + '  \n';
                    break;
                default:
                    continue;
            }
        }
    }
    return {
        name: name,
        declaration: declaration ? declaration : '',
        parameters: parameters,
        description: description,
        example: example
    };
}


function loadDefaultStubs(document: Buffer): BlitzStub[] {
    const r: BlitzStub[] = [];
    let descLines: string[] = [];
    let paramLines: string[] = [];
    let declaration: string = '';
    let name: string = '';
    let exampleLines: string[] = [];
    for (const line of document.toString().split(/\r\n|\r|\n/)) {
        if (line.trim().length == 0) {
            continue;
        }
        if (line.startsWith(';; ')) {
            descLines.push(line.substring(3));
        } else if (line.startsWith(';;param')) {
            paramLines.push(line.substring(7));
        } else if (line.startsWith('function')) {
            declaration = '(builtin)' + line.substring(8);
            name = declaration.substring(9);
            name = name.substring(0, name.indexOf('(') == -1 ? name.length : name.indexOf('(')).trim();
            if (!(name.startsWith('End') || name.startsWith('Else'))) name = name.substring(0, name.indexOf(' ') == -1 ? name.length : name.indexOf(' '));
            name = removeType(name);
        } else if (line.startsWith('end function')) {
            const snip = new vscode.SnippetString(name);
            const useBrackets = vscode.workspace.getConfiguration('blitz3d.editor').get<boolean>('UseBracketsEverywhere');
            const isKw = isBlitzKeyword(name.split(' ')[0]);
            if ((useBrackets && !isKw) || declaration.substring(9).includes('(')) snip.appendText('(');
            else snip.appendText(' ');
            const params: string[] = [];
            const dec = declaration.substring(10).replace('[', '').replace(/,\s*,/, ',').replace(']', '');
            const op = dec.indexOf('(') == -1 ? dec.indexOf(' ') : dec.indexOf('(');
            if (op > -1) {
                const cp = dec.indexOf(')') == -1 ? dec.length : dec.indexOf(')');
                const pars = dec.substring(op + 1, cp).split(',');
                for (const p of pars) params.push(removeType(p));
                for (let i = 0; i < params.length; i++) {
                    if (i < paramLines.length && paramLines[i].includes('optional')) continue;
                    if (i != 0) snip.appendText(', ');
                    snip.appendPlaceholder(removeType(params[i]));
                }
            }
            if ((useBrackets && !isKw) || declaration.substring(9).includes('(')) snip.appendText(')');
            r.push({
                name: name,
                declaration: declaration,
                description: descLines,
                parameters: paramLines,
                example: exampleLines.join('  \n'),
                snippet: snip
            });
            descLines = [];
            paramLines = [];
            exampleLines = [];
        } else {
            exampleLines.push(line);
        }
    }
    return r;
}
