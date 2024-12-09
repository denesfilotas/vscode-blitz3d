import * as vscode from 'vscode';
import { compilerVersion } from '../context';
import * as bb from '../types';
import { BlitzLegacyParser } from './legacy_parser';
import { Blitz117Parser } from './v117_parser';
import { Blitz118Parser } from './v118_parser';

export interface Parser {
    parse(intext: string, uri: vscode.Uri): bb.ParseResult;
    getResults(): bb.ParseResult;
}

export function getParser(bbtext: string, bburi: vscode.Uri): Parser {
    console.debug('switching on', compilerVersion);
    switch (compilerVersion) {
        case '11.17': return new Blitz117Parser(bbtext, bburi);
        case '11.18': return new Blitz118Parser(bbtext, bburi);
        default: return new BlitzLegacyParser(bbtext, bburi);
    }
}