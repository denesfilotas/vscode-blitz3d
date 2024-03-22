import * as vscode from 'vscode';

export type Ident = { ident: string, name: string; };

export type Token = {
    kind: string,
    ident: string,
    name: string,
    description?: string,
    uri: vscode.Uri,
    range: vscode.Range,
    declarationRange: vscode.Range;
};

export type Function = Token & {
    tag: string,
    params: Variable[],
    locals: Variable[],
    authors?: string[],
    returns?: string,
    deprecated?: string,
    since?: string;
};

export type Type = Token & {
    fields: Variable[],
    authors?: string[],
    since?: string;
};

export type Variable = Token & {
    tag: string,
    value?: any | undefined,
    constant: boolean;
};

export type Statement = any;

export type Data = any;

export type DimmedArray = Variable & {
    dimension: number
};

export type ExpressionKind = '?' | '%' | '#' | '$' | '.';

export type Expression = {
    kind: ExpressionKind,
    lhs?: Expression,
    rhs?: Expression,
    range: vscode.Range,
    uri: vscode.Uri,
    value?: any,
    type?: string;
};

export type ParseResult = {
    arrayDecls: Map<string, DimmedArray>,
    consts: Variable[],
    structs: Type[],
    funcs: Function[],
    datas: Data[],
    globals: Variable[],
    labels: Token[],
    diagnostics: Map<vscode.Uri, vscode.Diagnostic[]>;
};

export type AnalyzeResult = ParseResult & {
    locals: Variable[];
};

export type DeclParseResult = {
    funcs: Function[],
    diagnostics: Map<vscode.Uri, vscode.Diagnostic[]>;
};