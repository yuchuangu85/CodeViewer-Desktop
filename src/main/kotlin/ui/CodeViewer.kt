package ui

import common.Settings
import ui.editor.Editors
import java.io.File


class CodeViewer(
    val editors: Editors,
    val fileTree: File?,
    val settings: Settings
)