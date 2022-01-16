package ui

import CodeViewerApplicationState
import settings.CodeViewerSettings
import ui.editor.Editors
import ui.filetree.FileTree

class CodeViewerModel(
    val editors: Editors,
    val fileTree: FileTree,
)