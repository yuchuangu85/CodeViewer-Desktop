package ui.editor

import androidx.compose.runtime.mutableStateListOf
import common.CodeFile
import util.SingleSelection
import java.io.File

class Editors {
    private val selection = SingleSelection()

    var editors = mutableStateListOf<Editor>()
        private set

    val active: Editor? get() = selection.selected as Editor?

    /**
     * If no editor, new one and add to editors, else switch to it
     */
    fun open(codeFile: File) {
        var currentEditor: Editor? = null
        for (editor in editors) {
            if (editor.isSame(codeFile)) {
                currentEditor = editor;
                currentEditor.selection = selection
            }
        }
        if (currentEditor == null) {
            currentEditor = Editor(codeFile)
            currentEditor.selection = selection
            currentEditor.close = {
                close(currentEditor)
            }
            editors.add(currentEditor)
        }
        currentEditor.activate()
    }

    private fun close(editor: Editor) {
        val index = editors.indexOf(editor)
        editors.remove(editor)
        if (editor.isActive) {
            selection.selected = editors.getOrNull(index.coerceAtMost(editors.lastIndex))
        }
    }
}