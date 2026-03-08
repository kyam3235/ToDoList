package jp.kyamlab.todolist.data

import jp.kyamlab.todolist.model.ToDoItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ToDoRepositoryImpl : ToDoRepository {
    private val _items = MutableStateFlow(
        listOf(
            ToDoItem(id = "1", title = "Task 1", description = "Description 1", createdAt = 1000L),
            ToDoItem(id = "2", title = "Task 2", description = "Description 2", createdAt = 2000L),
            ToDoItem(id = "3", title = "Task 3", description = "Description 3", createdAt = 3000L)
        )
    )
    override val items: StateFlow<List<ToDoItem>> = _items.asStateFlow()

    override fun addItem(title: String, description: String) {
        val newItem = ToDoItem(
            id = kotlin.random.Random.nextLong().toString(),
            title = title,
            description = description,
            createdAt = 0L
        )
        _items.update { it + newItem }
    }

    override fun archiveItem(id: String) {
        _items.update { items -> items.map { if (it.id == id) it.copy(isArchived = true) else it } }
    }

    override fun unarchiveItem(id: String) {
        _items.update { items -> items.map { if (it.id == id) it.copy(isArchived = false) else it } }
    }

    override fun updateItem(item: ToDoItem) {
        _items.update { items -> items.map { if (it.id == item.id) item else it } }
    }
}
