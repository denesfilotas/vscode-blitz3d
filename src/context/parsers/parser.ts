import * as vscode from 'vscode';
import * as bb from '../types';
import { BlitzLegacyParser } from './legacy_parser';
import { Blitz117Parser } from './v117_parser';

export interface Parser {
    parse(intext: string, uri: vscode.Uri): bb.ParseResult;
    getResults(): bb.ParseResult;
}

export function getParser(bbtext: string, bburi: vscode.Uri): Parser {
    switch (vscode.workspace.getConfiguration('blitz3d.installation').get('SyntaxVersion')) {
        case 'v1.117': return new Blitz117Parser(bbtext, bburi);
        default: return new BlitzLegacyParser(bbtext, bburi);
    }
}