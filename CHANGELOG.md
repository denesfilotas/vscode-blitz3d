# Changelog

All major and minor version changes are listed here. Patch version changes should only contain bugfixes.
 > To see all changes in code since the first published version, check out the [GitHub repository](https://github.com/denesfilotas/vscode-blitz3d).

## 0.9.5 (pre-release)

- Improved handling of arrays introduced via the `Dim` keyword

## 0.9.4 (pre-release)

- automatic version detection
- support for Blitz3D 1.118

## 0.9.3 (pre-release)

- Initial support for modern Blitz3D up to version 1.117
- Dynamic function list generation (for LibSGD and custom builds)
- Support for multiple versions (ux will be revamped later)

## 0.9.2 (pre-release)

- Bugfixes regarding includes (by [@Aryan807](https://github.com/Aryan807))
- BBDoc support in userlibs
- Extended error checking

## 0.9.1 (pre-release)

- Rewritten parser based on the official source
- Added BBDoc keywords: `author`, `return`, `since` (by [@ZiYueCommentary](https://github.com/ZiYueCommentary/))
- Added support for deprecated functions in BBDoc, inline styles and code completion

## 0.4.0

- Stubs are no longer included with the extension
- Introduced support for userlibs
- Switched compilation to the main file instead of the open one
- Improved detection of multiple variables in the same line
- Diagnostics and performance improvements

## 0.3.0

- Added basic formatter functionality
- Improved signature help
- Parameter snippets
- Examples are replaced with links in hovers

## 0.2.0

- Support introduced for dimmed arrays
- Added command to open examples in the editor
- Automatically set the default encoding for bb files to Windows1250 on activation
- Performance and reliability improvements

## 0.1.0

- Initial release
