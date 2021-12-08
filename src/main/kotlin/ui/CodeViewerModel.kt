package ui

import common.Settings
import ui.editor.Editors
import ui.filetree.FileTree

class CodeViewerModel(
    val editors: Editors,
    val fileTree: FileTree,
    val settings: Settings
)