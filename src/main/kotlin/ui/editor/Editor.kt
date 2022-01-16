package ui.editor

import util.SingleSelection
import java.io.File

class Editor(
    val file: File,
) {
    var close: (() -> Unit)? = null
    lateinit var selection: SingleSelection

    val isActive: Boolean
        get() = selection.selected === this

    val isCode = file.name.endsWith(".kt", ignoreCase = true)

    fun activate() {
        selection.selected = this
    }

    fun isSame(file: File): Boolean {
        return this.file.absolutePath.equals(file.absolutePath)
    }
}
