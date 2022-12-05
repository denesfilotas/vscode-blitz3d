import * as vscode from 'vscode';
import * as path from 'path';
import * as cp from 'child_process';
import { readdirSync, readFileSync } from 'fs';
import { BlitzContext, BlitzDimmedArray, BlitzFunction, BlitzIterator, BlitzLabel, BlitzStub, BlitzToken, BlitzType, BlitzVariable } from '../util/types';
import { startOfComment, removeType, extractType } from '../util/functions';
import { diagnosticCollection } from './diagnostics';
import { showErrorOnCompile } from '../debug/compilation';

export let blitzCtx: BlitzContext = [];
export let blitzpath = 'C:\\Program Files (x86)\\Blitz3D';

export function updateBlitzPath() {
    const config: string | undefined = vscode.workspace.getConfiguration('blitz3d.installation').get('BlitzPath');
    blitzpath = config ? config : 'C:\\Program Files (x86)\\Blitz3D';
    const env = process.env;
    if (blitzpath.length > 0) {
        env['PATH'] += path.delimiter + path.join(blitzpath, 'bin');
        env['BLITZPATH'] = blitzpath;
    }
    cp.exec('blitzcc', env, (err, stdout, stderr) => {
        if (err) showErrorOnCompile(stdout, stderr);
    });
}

export function createLaunchContext() {
    const folder = vscode.workspace.workspaceFolders?.[0];
    const config = vscode.workspace.getConfiguration('launch', folder?.uri);
    let bburi;
    try {
        const bbfile = config.get<any[]>("configurations")?.[0].bbfile;
        const bbpath = path.isAbsolute(bbfile) ? bbfile : path.join(folder?.uri.path.substring(1) ?? '.', bbfile);
        bburi = vscode.Uri.file(bbpath);
        blitzCtx = generateContext(bburi, readFileSync(bbpath).toString());
    } catch {
        blitzCtx = [];
    }
}

function obtainWorkingDir(uri: vscode.Uri): string {
    const wsfolders = vscode.workspace.workspaceFolders;
    if (wsfolders && wsfolders.length == 1) {
        return wsfolders[0].uri.path.substring(1);
    }
    return uri.path.substring(1, uri.path.lastIndexOf('/'));
}

export function generateContext(uri: vscode.Uri, text: string, dir?: string | undefined, isUpdate?: boolean): BlitzContext {
    dir = dir || obtainWorkingDir(uri);
    let r: BlitzContext = [];
    const tokens: BlitzToken[] = [];
    let cType: BlitzType | undefined;
    let cStub: BlitzStub = new BlitzStub();
    let cFunction: BlitzFunction | undefined;
    let cIterator: BlitzIterator[] = [];
    const lines = text.split(/\r\n|\r|\n/);
    let diagnostics = diagnosticCollection.get(uri)?.filter(d => d.code !== 'blitz3d-include') ?? [];
    for (let i = 0; i < lines.length; i++) {
        const oline = lines[i];
        const tline = oline.toLowerCase().trim();
        const lineRange = new vscode.Range(i, 0, i, oline.length);

        // get tokens from included file
        if (tline.match(/^include\s/)) {
            if (!isUpdate) { // Don't return includes' tokens if only this file is changed
                const infile = tline.match(/(?<=\").+(?=\")/)?.toString();
                if (infile) {
                    let infilepath: string;
                    if (path.isAbsolute(infile)) {
                        infilepath = infile;
                    } else {
                        infilepath = path.join(dir, infile);
                    }
                    let intext = '';
                    try {
                        intext = readFileSync(infilepath).toString();
                    } catch (err) {
                        diagnostics.push({
                            message: "Unable to open include file",
                            range: lineRange,
                            severity: vscode.DiagnosticSeverity.Warning,
                            code: 'blitz3d-include'
                        });
                    }
                    const inuri = vscode.Uri.file(infilepath);
                    const inCtx = generateContext(inuri, intext, dir);
                    const inpaths = inCtx.map(c => c.uri.path);
                    if (intext.length > 0) r = r.filter(c => !inpaths.includes(c.uri.path)).concat(inCtx);
                }
            }
        }

        // change directory
        else if (tline.match(/^changedir\s/)) {
            const newdir = tline.match(/(?<=\").+(?=\")/)?.toString();
            if (newdir) {
                if (path.isAbsolute(newdir)) dir = newdir;
                else dir = path.normalize(path.join(dir, newdir));
            }
        }

        // parse global variables
        else if (tline.match(/^global\s/)) {
            const vars = oline.replace(/\(.*\)/g, '()').trimStart().substring(7, startOfComment(oline)).split(',').map(v => v.split('=')[0].trim());
            for (const v of vars) {
                if (v.includes(' ') || v.includes('\t')) break;
                if (v.length > 0) {
                    let bv = new BlitzVariable(
                        removeType(v),
                        uri,
                        'Global ' + v,
                        lineRange,
                        'global',
                        extractType(v)
                    );
                    bv.description = oline.substring(startOfComment(oline) + 1).trim();
                    tokens.push(bv);
                }
            }
        }

        // parse locals (or at least try)
        else if (tline.match(/^local\s/)) {
            const vars = oline.replace(/\(.*\)/g, '()').trimStart().substring(6, startOfComment(oline)).split(',').map(v => v.split('=')[0].trim());
            for (const v of vars) {
                if (v.includes(' ') || v.includes('\t')) break;
                if (v.length > 0) {
                    let bv = new BlitzVariable(
                        removeType(v),
                        uri,
                        'Local ' + v,
                        lineRange,
                        'local',
                        extractType(v)
                    );
                    bv.description = oline.substring(startOfComment(oline) + 1).trim();
                    if (cFunction) {
                        cFunction.locals.push(bv);
                    } else tokens.push(bv);
                }
            }
        }
        else if (tline.match(/^const\s/)) {
            const vars = oline.replace(/\(.*\)/g, '()').trimStart().substring(6, startOfComment(oline)).split(',').map(v => v.trim());
            for (const v of vars) {
                if (v.length > 0) {
                    let bv = new BlitzVariable(
                        removeType(v.split('=')[0]),
                        uri,
                        'Const ' + v.replace(/\s+/g, ' '),
                        lineRange,
                        'local',
                        extractType(v.split('=')[0])
                    );
                    bv.description = oline.substring(startOfComment(oline) + 1).trim();
                    if (cFunction) {
                        cFunction.locals.push(bv);
                    } else tokens.push(bv);
                }
            }
        }

        // parse iterators
        else if (tline.match(/^for\s/)) {
            const iter = oline.trimStart().substring(3).split('=')[0].trim();
            if (iter.length > 0) {
                let dt = extractType(iter);
                let l = false;
                if (cFunction) for (const tl of cFunction.locals) {
                    if (tl.lcname == removeType(tline.substring(4).split('=')[0].trim())) {
                        l = true;
                        break;
                    }
                } else for (const t of tokens) {
                    if (t.lcname == removeType(tline.substring(4).split('=')[0].trim())) {
                        l = true;
                        break;
                    }
                }
                if (!l) {
                    cIterator.push(new BlitzIterator(
                        removeType(oline.trimStart().substring(4).split('=')[0].trim()),
                        uri,
                        '(iterator) ' + oline.split('=')[0].trim().substring(4),
                        lineRange,
                        'local',
                        dt
                    ));
                }
            }
        }
        else if (tline.match(/^next\b/)) {
            const cit = cIterator.pop();
            if (cit) {
                cit.endPosition = lineRange.end;
                if (cFunction) cFunction.locals.push(cit);
                else tokens.push(cit);
            }
        }

        // parse locals with implicit declaration
        if (tline.indexOf('=') >= 0 && tline.indexOf('=') < startOfComment(tline)) {
            const pline = tline.split('=')[0].trim();
            if (!(pline.includes(' ') || pline.includes('\\') || pline.includes('(') || pline.includes('[') || pline.includes('\t'))) {

                let dt = extractType(tline.split('=')[0].trim());
                let l = false;
                if (cFunction) for (const tl of cFunction.locals) {
                    if (tl.lcname == removeType(tline.split('=')[0].trim())) {
                        l = true;
                        break;
                    }
                }
                if (!l) for (const t of tokens) {
                    if (t.lcname == removeType(tline.split('=')[0].trim())) {
                        l = true;
                        break;
                    }
                }
                if (!l) {
                    const v = new BlitzVariable(
                        removeType(oline.split('=')[0]),
                        uri,
                        '(Local) ' + oline.split('=')[0].trim(),
                        lineRange,
                        'local',
                        dt
                    );
                    v.description = oline.substring(startOfComment(oline) + 1).trim();
                    if (cFunction) cFunction.locals.push(v);
                    else tokens.push(v);
                }
            }
        }

        // parse bbdoc
        if (tline.match(/^;;\s/)) {
            cStub.description.push(oline.trim().substring(3));
        } else if (tline.match(/^;;param\s/)) {
            const fstspc = tline.indexOf(' ', 8);
            if (fstspc > -1) {
                const param = oline.trim().substring(7, fstspc);
                cStub.parameters.push('`' + param + '`' + oline.trim().substring(fstspc));
            } else cStub.parameters.push(oline.trim().substring(7));
        }

        // parse functions
        else if (tline.match(/^function\s/)) {
            const fn = oline.match(/(?<=\bfunction\b).+(?=\()/i)?.toString();
            if (!fn) continue;
            cFunction = new BlitzFunction(
                removeType(fn),
                uri,
                oline.trim().split(';')[0],
                lineRange
            );
            cFunction.uri = uri;
            if (cStub.description.length == 0 && i > 0) {
                const prevLine = lines[i - 1];
                if (prevLine.substring(0, startOfComment(prevLine)).trim() == '') cStub.description.push(prevLine.substring(startOfComment(prevLine) + 1).trim());
            }
            cFunction.stub = cStub;
            const fType = extractType(fn);
            if (fType) cFunction.returnType = fType;

            // parse parameters as local variables
            const op = tline.indexOf('(');
            if (op < startOfComment(tline)) { // don't parse parameters from comments
                const cp = tline.indexOf(')');
                if (cp >= 0) {
                    const pars = oline.trim().substring(op + 1, cp).split(',');
                    for (const q of pars) {
                        const name = q.split('=')[0].trim();
                        if (q.trim().length > 0) {
                            const param = new BlitzVariable(removeType(name), uri, '(param) ' + q.trim(), lineRange, 'param', extractType(name));
                            param.type = 'parameter';
                            cFunction.locals.push(param);
                        }
                    }
                }
            }

            cStub = new BlitzStub();
        } else if (cFunction && tline.startsWith('end function')) {
            cFunction.endPosition = lineRange.end;
            cFunction.range = new vscode.Range(cFunction.declarationRange.start, lineRange.end);
            tokens.push(cFunction);
            cFunction = undefined;
        }

        // parse types
        else if (tline.match(/^type\s/)) {
            const tn = oline.trimStart().substring(4).trim();
            if (tn.length > 0) cType = new BlitzType(tn, uri, oline.trim().split(';')[0], lineRange);
        }
        else if (cType && tline.startsWith('field')) {
            oline.trimStart().substring(5, startOfComment(oline.trimStart())).trim().split(',').forEach(val => {
                if (val.trim().length > 0) {
                    const field = new BlitzVariable(removeType(val), uri, 'Field ' + val.trim(), lineRange, 'field', extractType(val));
                    field.description = oline.substring(startOfComment(oline) + 1).trim();
                    field.matchBefore = /(Field\s.*|\\\s*)$/i;
                    field.type = 'field';
                    cType?.fields.push(field);
                }
            });
        }
        else if (cType && tline.startsWith('end type')) {
            cType.range = new vscode.Range(cType.declarationRange.start, lineRange.end);
            tokens.push(cType);
            cType = undefined;
        }

        // parse labels
        else if (tline.startsWith('.') && tline.length > 1) {
            tokens.push(new BlitzLabel(oline.trimStart().substring(1, startOfComment(oline.trimStart())), uri, '(label) ' + oline.trimStart(), lineRange));
        }

        // parse dimmed arrays
        else if (tline.match(/^dim\s/)) {
            let pline = oline.trimStart().substring(4);
            let arrname = pline.split('(')[0];
            const dt = extractType(arrname);
            arrname = removeType(arrname);
            const qline = pline.substring(pline.indexOf('(') + 1, pline.indexOf(')'));
            if (pline.length > 0 && arrname.length > 0) {
                const dims = qline.split(',').length;
                tokens.push(new BlitzDimmedArray(arrname, uri, oline.substring(0, startOfComment(oline)).trim(), lineRange, dims, dt));
            }
        }
    }
    diagnosticCollection.set(uri, diagnostics);
    r = r.filter(c => c.uri.path != uri.path);
    r.unshift({ uri, tokens });
    return r;
}


function loadUserLibs() {
    const r: BlitzContext = [];
    const declsFiles = readdirSync(path.join(blitzpath, 'userlibs')).filter(file => file.endsWith('decls'));
    for (const decls of declsFiles) {
        const tokens: BlitzToken[] = [];
        const fileName = path.join(blitzpath, 'userlibs', decls);
        const uri = vscode.Uri.file(fileName);
        const text = readFileSync(fileName).toString();
        const lines = text.split(/\r\n|\r|\n/);
        for (let i = 0; i < lines.length; i++) {
            const line = lines[i];
            const decl = line.split(':')[0].trim();
            if (decl.startsWith('.lib')) continue;
            const name = decl.split('(')[0].trim();
            if (name.length == 0) continue;
            const fun = new BlitzFunction(
                removeType(name),
                uri,
                'Function ' + decl,
                new vscode.Range(i, 0, i, line.length)
            );
            fun.returnType = '';
            tokens.push(fun);
        }
        if (tokens.length > 0) r.push({
            uri: uri,
            tokens: tokens
        });
    }
    return r;
}

export function initializeContext() {
    createLaunchContext();
    updateUserLibs();
}

export function updateContext(document: vscode.TextDocument) {
    const generatedCtx = generateContext(document.uri, document.getText());
    const generatedPaths = generatedCtx.map(gc => gc.uri.path);
    blitzCtx = blitzCtx.filter(c => !generatedPaths.includes(c.uri.path)).concat(generatedCtx);
}

export function updateUserLibs() {
    const userLibs = loadUserLibs();
    blitzCtx = blitzCtx.filter(c => !userLibs.map(lc => lc.uri.path).includes(c.uri.path)).concat(userLibs);
}