package ui

import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import common.AppTheme
import settings.CodeViewerSettings
import ui.editor.Editors
import ui.filetree.FileTree
import ui.theme.ThemeState
import window.CodeViewerWindowState

/**
 * Created by yuchuan
 * DATE 2021/12/7
 * TIME 23:27
 */
@Composable
fun CodeViewerMainView(
    state: CodeViewerWindowState,
    themeState: ThemeState
) {
    val codeViewerModel = remember {
        val editors = Editors()
        CodeViewerModel(
            editors = editors,
            fileTree = FileTree(state.file, editors),
        )
    }

    DisableSelection {
        MaterialTheme(
            colors = AppTheme.colors.material
        ) {
            Surface {
                CodeViewerView(codeViewerModel, themeState)
            }
        }
    }
}