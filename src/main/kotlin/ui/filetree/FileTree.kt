package ui.filetree

import common.CodeFile
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ui.editor.Editors

// 可展开文件（文件夹）
class ExpandableFile(
    val codeFile: CodeFile,
    val level: Int,
) {
    var children: List<ExpandableFile> by mutableStateOf(emptyList())
    val canExpand: Boolean get() = codeFile.hasChildren

    fun toggleExpanded() {
        children = if (children.isEmpty()) {
            codeFile.children
                .map { ExpandableFile(it, level + 1) }
                .sortedWith(compareBy({ it.codeFile.isDirectory }, { it.codeFile.name }))
                .sortedBy { !it.codeFile.isDirectory }
        } else {
            emptyList()
        }
    }
}

// 文件树
class FileTree(root: CodeFile, private val editors: Editors) {
    private val expandableRoot = ExpandableFile(root, 0).apply {
        toggleExpanded()
    }

    val items: List<Item> get() = expandableRoot.toItems()

    // 文件树每个子树
    inner class Item constructor(
        private val expandableFile: ExpandableFile
    ) {
        val name: String get() = expandableFile.codeFile.name

        val level: Int get() = expandableFile.level

        val type: ItemType
            get() = if (expandableFile.codeFile.isDirectory) {
                ItemType.Folder(isExpanded = expandableFile.children.isNotEmpty(), canExpand = expandableFile.canExpand)
            } else {
                ItemType.File(ext = expandableFile.codeFile.name.substringAfterLast(".").lowercase())
            }

        fun open() = when (type) {
            is ItemType.Folder -> expandableFile.toggleExpanded()
            is ItemType.File -> editors.open(expandableFile.codeFile)
        }
    }

    sealed class ItemType {
        class Folder(val isExpanded: Boolean, val canExpand: Boolean) : ItemType()
        class File(val ext: String) : ItemType()
    }

    private fun ExpandableFile.toItems(): List<Item> {
        fun ExpandableFile.addTo(list: MutableList<Item>) {
            list.add(Item(this))
            for (child in children) {
                child.addTo(list)
            }
        }

        val list = mutableListOf<Item>()
        addTo(list)
        return list
    }
}
