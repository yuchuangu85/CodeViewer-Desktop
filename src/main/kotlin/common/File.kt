@file:Suppress("NewApi")

package common

import kotlinx.coroutines.CoroutineScope
import util.TextLines

interface File {
    val name: String
    val isDirectory: Boolean
    val children: List<File>
    val hasChildren: Boolean

    fun readLines(scope: CoroutineScope): TextLines
}
