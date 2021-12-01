package ui

import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.window.FrameWindowScope
import androidx.compose.ui.window.MenuBar
import androidx.compose.ui.window.WindowPlacement
import common.AppTheme
import common.File
import common.Settings
import common.toProjectFile
import ui.editor.Editors
import kotlinx.coroutines.launch
import ui.filetree.FileTree
import window.NotepadWindowState

@Composable
fun MainView() {
    val codeViewer = remember {
        val editors = Editors()
        val homeFolder: File = java.io.File(System.getProperty("user.home")).toProjectFile()
        CodeViewer(
            editors = editors,
            fileTree = FileTree(homeFolder, editors),
            settings = Settings()
        )
    }

    DisableSelection {
        MaterialTheme(
            colors = AppTheme.colors.material
        ) {
            Surface {
                CodeViewerView(codeViewer)
            }
        }
    }
}
