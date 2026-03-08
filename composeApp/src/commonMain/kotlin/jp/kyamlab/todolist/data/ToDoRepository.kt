package jp.kyamlab.todolist.data

import jp.kyamlab.todolist.model.ToDoItem
import kotlinx.coroutines.flow.StateFlow

interface ToDoRepository {
    val items: StateFlow<List<ToDoItem>>
    fun addItem(title: String, description: String = "")
    fun archiveItem(id: String)
    fun unarchiveItem(id: String)
    fun updateItem(item: ToDoItem)
}
