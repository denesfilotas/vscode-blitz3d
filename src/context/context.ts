import * as cp from 'child_process';
import { readdirSync, readFileSync } from 'fs';
import * as path from 'path';
import { env } from 'process';
import * as vscode from 'vscode';
import { showErrorOnCompile } from '../debug/compilation';
import { DeclToker } from '../util/toker';
import { Analyzer, BlitzAnalyzer } from './analyzer';
import { semanticErrors, syntaxErrors, userLibErrors } from './diagnostics';
import { BlitzParser, Parser } from './parser';
import * as bb from './types';

export let userLibs: bb.Function[] = [];
export let blitzpath: string = vscode.workspace.getConfiguration('blitz3d.installation').get('BlitzPath') || env['BLITZPATH'] || '';
export let blitzCmd = blitzpath.length > 0 ? '"' + path.join(blitzpath, 'bin', 'blitzcc') + '"' : 'blitzcc';
export let parser: Parser;
export let parsed: bb.ParseResult;
export let analyzer: Analyzer;
export let analyzed: bb.AnalyzeResult;

export function updateBlitzPath(notify: boolean) {
    const config: string | undefined = vscode.workspace.getConfiguration('blitz3d.installation').get('BlitzPath');
    blitzpath = config || env['BLITZPATH'] || '';
    blitzCmd = blitzpath.length > 0 ? '"' + path.join(blitzpath, 'bin', 'blitzcc') + '"' : 'blitzcc';
    if (blitzpath.length > 0) env['BLITZPATH'] = blitzpath;
    cp.exec(blitzCmd, env, (err, stdout, stderr) => {
        if (err) showErrorOnCompile(stdout, stderr);
        else if (notify) vscode.window.showInformationMessage('BlitzPath updated.');
    });
}

export function createLaunchContext() {
    const folder = vscode.workspace.workspaceFolders?.[0];
    const config = vscode.workspace.getConfiguration('launch', folder?.uri);
    try {
        const bbfile = config.get<any[]>("configurations")?.[0].bbfile;
        const bbpath = path.isAbsolute(bbfile) ? bbfile : path.join(folder?.uri.path.substring(process.platform === 'win32' ? 1 : 0) ?? '.', bbfile);
        const bbtext = readFileSync(bbpath).toString();
        const bburi = vscode.Uri.file(bbpath);
        if (parser) {
            parsed = parser.parse(bbtext, bburi);
        } else {
            parser = new BlitzParser(bbtext, bburi);
            parsed = parser.getResults();
        }
        for (const [uri, diagnostics] of parsed.diagnostics) {
            syntaxErrors.set(uri, diagnostics);
        }
        if (analyzer) {
            analyzed = analyzer.analyze(bbtext, bburi, parsed);
        } else {
            analyzer = new BlitzAnalyzer(bbtext, bburi, parsed);
            analyzed = analyzer.getResults();
        }
        for (const [uri, diagnostics] of analyzed.diagnostics) {
            semanticErrors.set(uri, diagnostics);
        }
    } catch (err) {
        vscode.window.showErrorMessage('Error creating launch context: ' + err);
    }
}

function loadUserLibs(): bb.DeclParseResult {
    const r: bb.DeclParseResult = {
        funcs: [],
        diagnostics: new Map<vscode.Uri, vscode.Diagnostic[]>()
    };
    const basePath = blitzpath || process.env.BLITZPATH;
    if (!basePath) return r;
    let decls: any[] = []
    try {
        const files = readdirSync(path.join(basePath, 'userlibs')).filter(file => file.endsWith('decls'));
        const paths = files.map(file => path.join(basePath, 'userlibs', file));
        decls = paths.map(filePath => ({ toker: new DeclToker(readFileSync(filePath).toString()), uri: vscode.Uri.file(filePath) }));
        const openDocument = vscode.window.activeTextEditor?.document;
        if (openDocument?.languageId == 'blitz3d-decls') decls.push({
            toker: new DeclToker(openDocument.getText()),
            uri: openDocument.uri
        });
    } catch {
        vscode.window.showErrorMessage('Could not load userlibs');
        return r;
    }
    for (const decl of decls) {
        const toker = decl.toker;
        const uri = decl.uri;

        const funcs: bb.Function[] = [];
        const diagnostics: vscode.Diagnostic[] = [];
        let lib = [];
        toker.next();
        while (toker.curr() != 'eof') {
            if (toker.curr() == '.') {
                if (toker.next() != 'ident') diagnostics.push({
                    message: `Syntax error: expecting identifier, got ${toker.curr()}`,
                    range: toker.range(),
                    severity: vscode.DiagnosticSeverity.Error
                });
                if (toker.text() == 'lib') {
                    if (toker.next() != 'string') diagnostics.push({
                        message: `Syntax error: expecting library name, got ${toker.curr()}`,
                        range: toker.range(),
                        severity: vscode.DiagnosticSeverity.Error
                    });
                    else lib.push(toker.text());
                } else diagnostics.push({
                    message: `Syntax error: unknown directive ${toker.text()}`,
                    range: toker.range(),
                    severity: vscode.DiagnosticSeverity.Error
                });
                toker.next();
            } else if (toker.curr() == 'ident') {
                if (!lib.length) diagnostics.push({
                    message: `Function '${toker.text()}' cannot be declared without library`,
                    range: toker.range(),
                    severity: vscode.DiagnosticSeverity.Error
                });
                let name = toker.text();
                let ident = name.toLowerCase();
                if (funcs.find(fun => fun.ident == ident)) diagnostics.push({
                    message: `Duplicate identifier '${name}'`,
                    range: toker.range(),
                    severity: vscode.DiagnosticSeverity.Error
                });
                const tag = '%#$'.includes(toker.next()) ? toker.curr() : '.null';
                if ('%#$'.includes(tag)) toker.next();

                if (toker.curr() != '(') diagnostics.push({
                    message: `Syntax error: expecting '(' after function identifier, got ${toker.curr()}`,
                    range: toker.range(),
                    severity: vscode.DiagnosticSeverity.Error
                });
                else toker.next();
                const params: bb.Variable[] = [];
                if (toker.curr() != ')') {
                    for (; ;) {
                        if (toker.curr() != 'ident') break;
                        const pname = toker.text();
                        let maybe_tag = toker.next();
                        if ('%#$*'.includes(maybe_tag)) toker.next();
                        else maybe_tag = '';
                        params.push({
                            name: pname,
                            ident: pname.toLowerCase(),
                            constant: false,
                            declarationRange: toker.range(),
                            range: toker.range(),
                            kind: 'param',
                            tag: maybe_tag,
                            uri: uri
                        })
                        if (toker.curr() != ',') break;
                        toker.next();
                    }
                }
                if (toker.curr() != ')') diagnostics.push({
                    message: `Syntax error: expecting ')', got ${toker.curr()}`,
                    range: toker.range(),
                    severity: vscode.DiagnosticSeverity.Error
                });

                if (toker.next() == ':') {
                    toker.next();
                    if (toker.curr() != 'ident' && toker.curr() != 'string') diagnostics.push({
                        message: `Syntax error: expecting identifier or string, got ${toker.curr()}`,
                        range: toker.range(),
                        severity: vscode.DiagnosticSeverity.Error
                    });
                    name = toker.text();
                    ident = name.toLowerCase();
                    toker.next();
                }
                funcs.push({
                    ident: ident,
                    name: name,
                    kind: 'userlib',
                    locals: [],
                    params: params,
                    declarationRange: toker.range(), // TODO,
                    range: toker.range(),
                    tag: tag,
                    uri: uri,
                    description: `From lib "${lib[lib.length - 1]}"`
                });
            } else {
                diagnostics.push({
                    message: `Syntax error: unexpected ${toker.curr()}`,
                    range: toker.range(),
                    severity: vscode.DiagnosticSeverity.Error
                });
                toker.next();
            }
        }
        r.funcs.push(...funcs);
        r.diagnostics.set(uri, diagnostics);
    }
    return r;
}

export function initializeContext() {
    createLaunchContext();
    updateUserLibs();
}

export function updateContext(document: vscode.TextDocument) {
    if (document.languageId == 'blitz3d') {
        parsed = parser.parse(document.getText(), document.uri);
        analyzed = analyzer.analyze(document.getText(), document.uri, parsed);
    } else if (document.languageId == 'blitz3d-decls') {

    }
    for (const [uri, diagnostics] of parsed.diagnostics) {
        syntaxErrors.set(uri, diagnostics);
    }
    for (const [uri, diagnostics] of analyzed.diagnostics) {
        semanticErrors.set(uri, diagnostics);
    }
}

export function updateUserLibs() {
    const libs = loadUserLibs();
    userLibs = libs.funcs;
    for (const [uri, diagnostics] of libs.diagnostics) {
        userLibErrors.set(uri, diagnostics);
    }
}