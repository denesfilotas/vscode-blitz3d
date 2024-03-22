# Language support for Blitz3D

This extension enables language support for the BlitzBasic language and extends the usage of the Blitz3D program and its `blitzcc` compiler.

> Note: This is a pre-release version. You might experience more bugs than usual.

## Release Notes

> To see all the changes, check the [changelog](CHANGELOG.md) or the [GitHub repository](https://github.com/denesfilotas/vscode-blitz3d).

In this update the whole parser has been rewritten based on the official Blitz3D source. This results in some missing functionality from previous versions.

- Added BBDoc keywords: `author`, `return`, `since` (by [@ZiYueCommentary](https://github.com/ZiYueCommentary/))
- Added support for deprecated functions in BBDoc, inline styles and code completion

## Features

The extension provides the following functionalities:

- Syntax highlighting
- Syntax error reporting
- Semantic highlighting
- Some semantic warnings and errors (WIP)
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

The following keywords can be used in lines preceding function definitions:

- `;; ` followed by the documentation of the function
- `;;param ` followed by the name of a parameter and its description
- `;;author ` followed by the author of the function
- `;;return ` followed by the return value of the function
- `;;since ` followed by the version when the function was created
- `;;deprecated`, optionally followed by a reasoning or alternatives

These keywords are interpreted as comments in standard blitz3d, but within vscode they are used to provide information on mouse hover or code completion.

### Type documentation

The following keywords can be used in lines preceding type definitions:

- `;; ` followed by the documentation of the type
- `;;author ` followed by the author of the type
- `;;since ` followed by the version when the type was created

### Manage tasks with TODOS

Use `;;TODO` anywhere in the code to create a link for a task in vscode's problems window. This can be disabled in the extension settings.

### Information interpreted without keywords

Comments following global, local, const or field declarations are interpreted as descriptions, which are shown in code completion windows.

## Extension Settings

See contributed settings in the `Feature Contributions` tab.

## Known Issues and limitaions

- Include files might not be recognized when filename is not explicitly specified or after using changedir
- Userlibs are only partially supported
  - Functions without a decorated name (alias) are not syntax highlighted
  - BBDoc is not available yet for userlib-defined functions
- Information provided on hover might be incorrect if there are more than one objects with the same name (like a function and a type)
- Type checking
