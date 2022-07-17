# Language support for Blitz3D

This extension enables language support for the BlitzBasic language and extends the usage of the Blitz3D program and its `blitzcc` compiler.
The ability to run programs and detect errors is supported on Windows only. Cross-platform Blitz3D versions are not supported yet, though most of the functionality is available on all platforms.
> Note: This extension is in alpha phase. Bug-free experience cannot be guaranteed.


## Release Notes

This update adds the following features:

- Basic formatter functionality
- Improved signature help
- Parameter snippets
- Examples are replaced with links in hovers
- Other bug fixes and performance improvements

> To see all the changes, check the [changelog](CHANGELOG.md) or the [GitHub repository](https://github.com/denesfilotas/vscode-blitz3d).

## Features

The extension provides the following functionalities:

- Syntax highlighting
- Semantic highlighting
- Basic IntelliSense suggestions
- Document symbols tree
- Signature help
- Usage of the built-in debugger GUI
- Added functionality for documentation
- Ability to set the default file to run in launch.json
- First compile error can be seen on every save
- Command for browsing the builtin code examples
- Basic document formatting

> Note: The extension uses the Blitz3D default compiler for Windows. Due to its limitations, information of runtime errors cannot be extracted. This is not a loss of functionality as the default IDE behaves similarly.

## BBDoc: javadoc-like documentation utilities

BBDoc helps to write code more efficiently with some added keywords, without affecting compatibility.

### Function documentation

The following keywords can be used in lines preceding function declarations:

- `;; ` followed by the documentation of the function
- `;;param ` followed by the name of a parameter and its description

These keywords are interpreted as comments in standard blitz3d, but within vscode they are used to provide information on mouse hover or code completion.
If there is no bbdoc for a function and the preceding line contains just a comment, that is parsed as description.

### Manage tasks with TODOS

Use `;;TODO` anywhere in the code to create a link for a task in vscode's problems window. This can be disabled in the extension settings.

### Information interpreted without keywords

Comments following global, local, const or field declarations are interpreted as descriptions, which are shown in code completion windows.

## Extension Settings

See contributed settings in the `Feature Contributions` tab.

## Known Issues

 * Variables might not be recognized when no explicit declaration is present
 * Changedir command prevents the extension from importing files correctly
 * Custom commands and DLLs are not supported
 * Hangs and unexpected behaviour (without code loss) can be experienced rarely
