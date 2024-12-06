import * as cp from 'child_process';
import { readdirSync, readFileSync } from 'fs';
import * as path from 'path';
import { env } from 'process';
import * as vscode from 'vscode';
import { showErrorOnCompile } from '../debug/compilation';
import { DeclToker } from '../util/toker';
import { Analyzer, getAnalyzer } from './analyzers/analyzer';
import { semanticErrors, syntaxErrors, userLibErrors } from './diagnostics';
import { Parser, getParser } from './parsers/parser';
import * as bb from './types';
import { removeType } from '../util/functions';

export let userLibs: bb.Function[] = [];
export let blitzpath: string = vscode.workspace.getConfiguration('blitz3d.installation').get('BlitzPath') || env['BLITZPATH'] || '';
export let blitzCmd = blitzpath.length > 0 ? '"' + path.join(blitzpath, 'bin', process.platform === 'win32' ? 'blitzcc.exe' : 'blitzcc') + '"' : 'blitzcc';
export let builtinFunctions: string[] = [];
export let builtinFunctionsLower: string[] = [];
export let parser: Parser;
export let parsed: bb.ParseResult;
export let analyzer: Analyzer;
export let analyzed: bb.AnalyzeResult;

export function updateBlitzPath(notify: boolean) {
    const config: string | undefined = vscode.workspace.getConfiguration('blitz3d.installation').get('BlitzPath');
    blitzpath = config || env['blitzpath'] || '';
    blitzCmd = blitzpath.length > 0 ? '"' + path.join(blitzpath, 'bin', process.platform === 'win32' ? 'blitzcc.exe' : 'blitzcc') + '"' : 'blitzcc';
    if (blitzpath.length > 0) env['blitzpath'] = blitzpath;
    cp.exec(blitzCmd, {env}, (err, stdout, stderr) => {
        if (err) showErrorOnCompile(stdout, stderr);
        else if (notify) vscode.window.showInformationMessage('BlitzPath updated.');
    });
    // detect functions
    cp.exec(`${blitzCmd} -k`, {env}, (err, stdout, stderr) => {
        if (err || stderr) builtinFunctions = [];
        builtinFunctions = stdout.split(/\r\n|\r|\n/);
        builtinFunctionsLower = stdout.toLowerCase().split(/\r\n|\r|\n/);
        const document = vscode.window.activeTextEditor?.document;
        if (document) updateContext(document);
    });
    // TODO detect version and runtime
}

export function createLaunchContext() {
    try {
        const folder = vscode.workspace.workspaceFolders?.[0];
        const config = vscode.workspace.getConfiguration('launch', folder?.uri);
        const bbfile = config.get<any[]>("configurations")?.[0]?.bbfile;
        if (!bbfile) return;
        const bbpath = path.isAbsolute(bbfile) ? bbfile : path.join(folder?.uri.path.substring(process.platform === 'win32' ? 1 : 0) ?? '.', bbfile);
        const bbtext = readFileSync(bbpath).toString();
        const bburi = vscode.Uri.file(bbpath);
        if (parser) {
            parsed = parser.parse(bbtext, bburi);
        } else {
            parser = getParser(bbtext, bburi);
            parsed = parser.getResults();
        }
        for (const [uri, diagnostics] of parsed.diagnostics) {
            syntaxErrors.set(uri, diagnostics);
        }
        if (analyzer) {
            analyzed = analyzer.analyze(bbtext, bburi, parsed);
        } else {
            analyzer = getAnalyzer(bbtext, bburi, parsed);
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
    let decls: any[] = [];
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
        const toker: DeclToker = decl.toker;
        const uri = decl.uri;

        const funcs: bb.Function[] = [];
        const diagnostics: vscode.Diagnostic[] = [];

        let bbdoc: {
            descLines: string[],
            paramLines: Map<string, string>,
            authors: string[],
            returns: string,
            since: string,
            deprecated: string | undefined;
        } = {
            descLines: [],
            paramLines: new Map<string, string>(),
            authors: [],
            returns: '',
            since: '',
            deprecated: undefined
        };

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
            } else if (toker.curr() == 'comment') {
                const comment = toker.text();
                if (comment.startsWith(';; ')) {
                    bbdoc.descLines.push(comment.substring(3));
                } else if (comment.startsWith(';;param ')) {
                    const line = comment.substring(8);
                    if (line.includes(' ')) {
                        const param = line.substring(0, line.indexOf(' '));
                        const desc = line.substring(line.indexOf(' ') + 1);
                        bbdoc.paramLines.set(removeType(param.toLowerCase()), desc);
                    } else {
                        diagnostics.push({
                            message: `BBDoc: incomplete parameter description`,
                            range: toker.range(),
                            severity: vscode.DiagnosticSeverity.Warning
                        });
                    }
                } else if (comment.startsWith(';;author ')) {
                    bbdoc.authors.push(comment.substring(9));
                } else if (comment.startsWith(';;return ')) {
                    bbdoc.returns = comment.substring(9);
                } else if (comment.startsWith(';;since ')) {
                    bbdoc.since = comment.substring(8);
                } else if (comment.startsWith(';;deprecated')) {
                    bbdoc.deprecated = comment.substring(12).trim();
                } else if (comment.startsWith(';;') && !comment.startsWith(';;todo')) {
                    diagnostics.push({
                        message: `Invalid BBDoc`,
                        range: toker.range(),
                        severity: vscode.DiagnosticSeverity.Warning
                    });
                }
                toker.next();
            } else if (toker.curr() == 'ident') {
                if (!lib.length) diagnostics.push({
                    message: `Function '${toker.text()}' cannot be declared without library`,
                    range: toker.range(),
                    severity: vscode.DiagnosticSeverity.Error
                });
                const name = toker.text();
                const ident = name.toLowerCase();
                if (funcs.find(fun => fun.ident == ident)) diagnostics.push({
                    message: `Duplicate identifier '${name}'`,
                    range: toker.range(),
                    severity: vscode.DiagnosticSeverity.Error
                });
                const tag = '%#$'.includes(toker.next()) ? toker.curr() : 'null';
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
                            uri: uri,
                            description: bbdoc.paramLines.get(pname.toLowerCase())
                        });
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
                    description: `${bbdoc.descLines.join('  \n')}  \nFrom lib "${lib[lib.length - 1]}"`,
                    authors: bbdoc.authors,
                    deprecated: bbdoc.deprecated,
                    returns: bbdoc.returns,
                    since: bbdoc.since
                });
                bbdoc = {
                    descLines: [],
                    paramLines: new Map<string, string>(),
                    authors: [],
                    returns: '',
                    since: '',
                    deprecated: undefined
                };
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
        if (parser) {
            parsed = parser.parse(document.getText(), document.uri);
        } else {
            parser = getParser(document.getText(), document.uri);
            parsed = parser.getResults();
        }
        for (const [uri, diagnostics] of parsed.diagnostics) {
            syntaxErrors.set(uri, diagnostics);
        }
        if (analyzer) {
            analyzed = analyzer.analyze(document.getText(), document.uri, parsed);
        } else {
            analyzer = getAnalyzer(document.getText(), document.uri, parsed);
            analyzed = analyzer.getResults();
        }
        for (const [uri, diagnostics] of analyzed.diagnostics) {
            semanticErrors.set(uri, diagnostics);
        }
    } else if (document.languageId == 'blitz3d-decls') {
        loadUserLibs();
    }
}

export function updateUserLibs() {
    const libs = loadUserLibs();
    userLibs = libs.funcs;
    for (const [uri, diagnostics] of libs.diagnostics) {
        userLibErrors.set(uri, diagnostics);
    }
}

/**
 * Get the include directory of the blitz program.
 * The program is either specified in the launch config, or is the open file.
 * @param uri The uri of the open bb file
 * @returns the directory relative to which the program can include
 */
export function obtainWorkingDir(uri: vscode.Uri): string {
    const config = vscode.workspace.getConfiguration('launch', uri);
    const launchConfigs = config.get<any[]>("configurations");
    if (!launchConfigs) return uri.path.substring(process.platform === 'win32' ? 1 : 0, uri.path.lastIndexOf('/'));
    for (const launch of launchConfigs) {
        if (launch.type == 'blitz3d' && launch.bbfile) {
            if (path.isAbsolute(launch.bbfile)) return launch.bbfile;
            const wsFolder = vscode.workspace.workspaceFolders?.find(folder => uri.path.startsWith(folder.uri.path));
            const wspath = wsFolder?.uri.path.substring(process.platform === 'win32' ? 1 : 0, uri.path.lastIndexOf('/')) || '';
            return path.join(wspath, launch.bbfile.substring(0, launch.bbfile.lastIndexOf('/')));
        }
    }
    return uri.path.substring(process.platform === 'win32' ? 1 : 0, uri.path.lastIndexOf('/'));
}
