import * as vscode from 'vscode';
import { compilerVersion } from '../context';
import * as bb from '../types';
import { BlitzLegacyAnalyzer } from './legacy_analyzer';
import { Blitz117Analyzer } from './v117_analyzer';
import { Blitz118Analyzer } from './v118_analyzer';

export interface Analyzer {
    analyze(intext: string, uri: vscode.Uri, parsed: bb.ParseResult): bb.AnalyzeResult;
    getResults(): bb.AnalyzeResult;
}

export function getAnalyzer(bbtext: string, bburi: vscode.Uri, parsed: bb.ParseResult): Analyzer {
    switch (compilerVersion) {
        case '11.8':
        case '2.0':
            return new BlitzLegacyAnalyzer(bbtext, bburi, parsed);
        case '11.17':
            return new Blitz117Analyzer(bbtext, bburi, parsed);
        case '11.18':
        default:
            return new Blitz118Analyzer(bbtext, bburi, parsed);
    }
}