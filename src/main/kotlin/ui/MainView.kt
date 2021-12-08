package ui

import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import common.AppTheme
import common.Settings
import common.toProjectFile
import ui.editor.Editors
import ui.filetree.FileTree
import window.CodeViewerWindowState

/**
 * Created by yuchuan
 * DATE 2021/12/7
 * TIME 23:27
 */
@Composable
fun MainView(state: CodeViewerWindowState) {
    val codeViewer = remember {
        val editors = Editors()
        CodeViewer(
            editors = editors,
            fileTree = FileTree(state.file.toProjectFile(), editors),
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