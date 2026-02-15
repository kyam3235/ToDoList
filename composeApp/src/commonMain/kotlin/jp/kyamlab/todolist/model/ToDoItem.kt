package jp.kyamlab.todolist.model

data class ToDoItem(
    val id: String,
    val title: String,
    val description: String = "",
    val isArchived: Boolean = false,
    val createdAt: Long = 0L
)
