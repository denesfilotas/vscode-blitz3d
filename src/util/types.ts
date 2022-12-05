import * as vscode from 'vscode';

export class BlitzStub {
    name: string = '';
    declaration: string = '';
    parameters: string[] = [];
    description: string[] = [];
    example: string = '';
    snippet?: vscode.SnippetString;
}

export class BlitzToken {
    lcname: string;
    oname: string;
    declaration: string;
    uri: vscode.Uri;
    declarationRange: vscode.Range;
    range: vscode.Range;
    references: vscode.Range[];
    type: string = '';

    matchBefore: string | RegExp | undefined;
    matchAfter: string | RegExp | undefined;

    constructor(name: string, uri: vscode.Uri, declaration: string, declarationRange: vscode.Range) {
        if (name.length == 0) name = '<unnamed>';
        this.oname = name;
        this.lcname = name.toLowerCase();
        this.uri = uri;
        this.declaration = declaration;
        this.declarationRange = declarationRange;
        this.range = declarationRange;
        this.references = [];
    }
}

export class BlitzFunction extends BlitzToken {
    type: string = 'function';
    returnType: string = '(%)';
    locals: BlitzVariable[] = [];
    endPosition: vscode.Position = this.declarationRange.end;
    stub: BlitzStub | undefined;
}

export class BlitzType extends BlitzToken {
    type: string = 'type';
    matchBefore: string | RegExp | undefined = /(\.|new\s+|each\s+|type\s+|first\s+|last\s+)$/i;
    fields: BlitzVariable[] = [];
}

export class BlitzVariable extends BlitzToken {
    storageType: string = 'local';
    dataType: string = '(%)';
    matchBefore: string | RegExp | undefined = /(?<!\.|new\s|\\|each\s)$/i;
    description: string | undefined;
    constructor(name: string, uri: vscode.Uri, declaration: string, declarationRange: vscode.Range, storageType?: string, dataType?: string) {
        super(name, uri, declaration, declarationRange);
        this.type = 'variable';
        if (storageType) this.storageType = storageType;
        if (dataType) this.dataType = dataType;
    }
}

export class BlitzLabel extends BlitzToken {
    type: string = 'label';
    matchBefore: string | RegExp | undefined = /\.|goto\s/i;
}

// TODO: create interface for limited accessibility
export class BlitzIterator extends BlitzVariable {
    endPosition: vscode.Position = new vscode.Position(0, 0);
}

export class BlitzDimmedArray extends BlitzToken {
    dimension: number;
    dataType: string = '(%)';
    constructor(name: string, uri: vscode.Uri, declaration: string, declarationRange: vscode.Range, dimension: number, dataType?: string) {
        super(name, uri, declaration, declarationRange);
        this.dimension = dimension;
        this.type = 'array';
        if (dataType) this.dataType = dataType;
    }
}

export type BlitzTokenCollection = {
    uri: vscode.Uri,
    tokens: BlitzToken[];
};
export type BlitzContext = BlitzTokenCollection[];
