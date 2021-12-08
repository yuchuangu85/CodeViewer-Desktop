package ui

import common.Settings
import ui.editor.Editors
import ui.filetree.FileTree
import java.io.File


class CodeViewer(
    val editors: Editors,
    val fileTree: FileTree,
    val settings: Settings
)