import { DebugSession, ExitedEvent, OutputEvent, TerminatedEvent } from '@vscode/debugadapter';
import { DebugProtocol } from '@vscode/debugprotocol';
import * as cp from 'child_process';
import * as treekill from 'tree-kill';
import * as vscode from 'vscode';
import { blitzCmd, blitzpath } from '../context/context';

export default class DebugAdapterDescriptorFactory implements vscode.DebugAdapterDescriptorFactory {
    createDebugAdapterDescriptor(session: vscode.DebugSession, executable: vscode.DebugAdapterExecutable | undefined): vscode.ProviderResult<vscode.DebugAdapterDescriptor> {
        return new vscode.DebugAdapterInlineImplementation(new DebugAdapter());
    }
}

interface BlitzLaunchRequestArguments extends DebugProtocol.LaunchRequestArguments {
    bbfile: string;
}

class DebugAdapter extends DebugSession {

    debugProcess: cp.ChildProcess | undefined;
    bbfile = '';
    static seq = 0;

    constructor() {
        super();
    }

    protected launchRequest(response: DebugProtocol.LaunchResponse, args: BlitzLaunchRequestArguments, request?: DebugProtocol.Request | undefined): void {
        const cmd = `${blitzCmd} ${args.noDebug ? ' ' : ' -d '} "${args.bbfile}"`;
        const env = process.env;
        if (blitzpath.length > 0) env['BLITZPATH'] = blitzpath;
        this.debugProcess = cp.exec(cmd, env);
        this.debugProcess.addListener('exit', code => this.sendEvent(new ExitedEvent(code ? code : 0)));
        this.debugProcess.addListener('close', () => this.sendEvent(new TerminatedEvent(false)));
        this.debugProcess.addListener('error', err => this.sendEvent(new OutputEvent('[ERROR] ' + err, 'console')));
        this.debugProcess.stdout?.addListener('data', data => this.sendEvent(new OutputEvent(data, 'stdout')));
        this.debugProcess.stderr?.addListener('data', data => this.sendEvent(new OutputEvent(data, 'stderr')));
        this.sendResponse(response);
    }

    protected disconnectRequest(response: DebugProtocol.DisconnectResponse, args: DebugProtocol.DisconnectArguments, request?: DebugProtocol.Request | undefined): void {
        if (this.debugProcess && this.debugProcess.exitCode === null) {
            this.debugProcess.removeAllListeners();
            this.debugProcess.stdout?.removeAllListeners();
            this.debugProcess.stderr?.removeAllListeners();
            treekill(this.debugProcess.pid, error => {
                if (error) {
                    vscode.window.showErrorMessage('Error killing child process: ' + error);
                    this.sendErrorResponse(response, 1);
                    return;
                }
            });
        } else this.sendResponse(response);
    }
}