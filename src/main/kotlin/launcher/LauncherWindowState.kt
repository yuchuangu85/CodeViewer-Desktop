package launcher

import CodeViewerApplicationState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowState
import window.DialogState

/**
 * Created by yuchuan.gu
 * DATE 2021/12/8
 * TIME 11:57
 */
class LauncherWindowState(
    private val application: CodeViewerApplicationState,
    private val exit: (LauncherWindowState) -> Unit,
    private val openEditor: (LauncherWindowState) -> Unit,
) {
    val window = WindowState(
        size = DpSize(800f.dp, 500f.dp),
    )

    var file by mutableStateOf(java.io.File(""))
        private set

    val openDialog = DialogState<java.io.File?>()

    suspend fun chooseFile() {
        val file = openDialog.awaitResult()
        println("chooseFile: ${file?.path}")
        if (file != null) {
            this.file = file
            openEditor(this)
            exit(this)
        }
    }

    suspend fun exit(): Boolean {
        exit(this)
        return true
    }

    suspend fun run() {

    }
}