import * as vscode from 'vscode';
import * as bb from '../types';
import { BlitzLegacyAnalyzer } from './legacy_analyzer';
import { Blitz117Analyzer } from './v117_analyzer';

export interface Analyzer {
    analyze(intext: string, uri: vscode.Uri, parsed: bb.ParseResult): bb.AnalyzeResult;
    getResults(): bb.AnalyzeResult;
}

export function getAnalyzer(bbtext: string, bburi: vscode.Uri, parsed: bb.ParseResult): Analyzer {
    switch(vscode.workspace.getConfiguration('blitz3d.installation').get('SyntaxVersion')) {
        case 'v1.117': return new Blitz117Analyzer(bbtext, bburi, parsed);
        default: return new BlitzLegacyAnalyzer(bbtext, bburi, parsed);
    }
}