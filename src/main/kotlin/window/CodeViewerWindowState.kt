package window

import CodeViewerApplicationState
import androidx.compose.runtime.*
import androidx.compose.ui.window.Notification
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowState
import common.Settings
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import util.AlertDialogResult
import java.nio.file.Path


class CodeViewerWindowState(
    private val application: CodeViewerApplicationState,
    file: java.io.File,
    private val exit: (CodeViewerWindowState) -> Unit
) {
    val settings: Settings get() = application.settings

    val window = WindowState()

    var isChanged by mutableStateOf(false)
        private set

    var file by mutableStateOf(file)
        private set

    val openDialog = DialogState<java.io.File?>()
    val saveDialog = DialogState<java.io.File?>()
    val exitDialog = DialogState<AlertDialogResult>()

    private var _notifications = Channel<NotepadWindowNotification>(0)
    val notifications: Flow<NotepadWindowNotification> get() = _notifications.receiveAsFlow()

    private var _text by mutableStateOf("")

    var text: String
        get() = _text
        set(value) {
            check(isInit)
            _text = value
            isChanged = true
        }

    var isInit by mutableStateOf(false)
        private set

    fun toggleFullscreen() {
        window.placement = if (window.placement == WindowPlacement.Fullscreen) {
            WindowPlacement.Floating
        } else {
            WindowPlacement.Fullscreen
        }
    }

    suspend fun run() {
        open(file)
    }

    private suspend fun open(file: java.io.File) {
        isInit = false
        isChanged = false
        this.file = file
        try {
            _text = file.resolve(file).toPath().readTextAsync()
            isInit = true
        } catch (e: Exception) {
            e.printStackTrace()
            text = "Cannot read $file"
        }
    }

    private fun initNew() {
        _text = ""
        isInit = true
        isChanged = false
    }

    fun newWindow() {
//        application.newWindow()
    }

    suspend fun open() {
        if (askToSave()) {
            val path = openDialog.awaitResult()
            if (path != null) {
                open(path)
            }
        }
    }

    suspend fun save(): Boolean {
        check(isInit)
//        if (file == null) {
//            val file = saveDialog.awaitResult()
//            if (path != null) {
//                save(path)
//                return true
//            }
//        } else {
//            save(path!!)
//            return true
//        }
        return true
    }

    private var saveJob: Job? = null

    private suspend fun save(file: java.io.File) {
        isChanged = false
        this.file = file

        saveJob?.cancel()
        saveJob = file.resolve(file).toPath().launchSaving(text)

        try {
            saveJob?.join()
            _notifications.trySend(NotepadWindowNotification.SaveSuccess(file))
        } catch (e: Exception) {
            isChanged = true
            e.printStackTrace()
            _notifications.trySend(NotepadWindowNotification.SaveError(file))
        }
    }

    suspend fun exit(): Boolean {
        return if (askToSave()) {
            exit(this)
            true
        } else {
            false
        }
    }

    private suspend fun askToSave(): Boolean {
        if (isChanged) {
            when (exitDialog.awaitResult()) {
                AlertDialogResult.Yes -> {
                    if (save()) {
                        return true
                    }
                }
                AlertDialogResult.No -> {
                    return true
                }
                AlertDialogResult.Cancel -> return false
            }
        } else {
            return true
        }

        return false
    }

    fun sendNotification(notification: Notification) {
        application.sendNotification(notification)
    }
}

@OptIn(DelicateCoroutinesApi::class)
private fun Path.launchSaving(text: String) = GlobalScope.launch {
    writeTextAsync(text)
}

private suspend fun Path.writeTextAsync(text: String) = withContext(Dispatchers.IO) {
    val file = toFile()
    if (file.isFile) {
        file.writeText(text)
    } else {
        ""
    }
}

private suspend fun Path.readTextAsync() = withContext(Dispatchers.IO) {
    val file = toFile()
    if (file.isFile) {
        file.readText()
    } else {
        ""
    }
}

sealed class NotepadWindowNotification {
    class SaveSuccess(val file: java.io.File) : NotepadWindowNotification()
    class SaveError(val file: java.io.File) : NotepadWindowNotification()
}

class DialogState<T> {
    private var onResult: CompletableDeferred<T>? by mutableStateOf(null)

    val isAwaiting get() = onResult != null

    suspend fun awaitResult(): T {
        onResult = CompletableDeferred()
        val result = onResult!!.await()
        onResult = null
        return result
    }

    fun onResult(result: T) = onResult!!.complete(result)
}