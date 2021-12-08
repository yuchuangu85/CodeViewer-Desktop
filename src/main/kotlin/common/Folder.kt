@file:Suppress("NewApi")

package common

import kotlinx.coroutines.CoroutineScope
import util.TextLines

interface Folder {
    val name: String
    val isDirectory: Boolean
    val children: List<Folder>
    val hasChildren: Boolean

    fun readLines(scope: CoroutineScope): TextLines
}
