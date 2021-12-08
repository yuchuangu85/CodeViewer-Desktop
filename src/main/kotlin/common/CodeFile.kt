@file:Suppress("NewApi")

package common

import kotlinx.coroutines.CoroutineScope
import util.TextLines

interface CodeFile {
    val name: String
    val isDirectory: Boolean
    val children: List<CodeFile>
    val hasChildren: Boolean

    fun readLines(scope: CoroutineScope): TextLines
}
