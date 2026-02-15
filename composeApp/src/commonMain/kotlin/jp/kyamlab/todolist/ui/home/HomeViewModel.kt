package jp.kyamlab.todolist.ui.home

import androidx.lifecycle.ViewModel
import jp.kyamlab.todolist.model.ToDoItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        // Dummy data for initial display
        _uiState.update { currentState ->
            currentState.copy(
                items = listOf(
                    ToDoItem(id = "1", title = "Task 1", description = "Description 1", createdAt = 1000L),
                    ToDoItem(id = "2", title = "Task 2", description = "Description 2", createdAt = 2000L),
                    ToDoItem(id = "3", title = "Task 3", description = "Description 3", createdAt = 3000L)
                )
            )
        }
    }

    fun addItem(title: String) {
        val newItem = ToDoItem(
            id = kotlin.random.Random.nextLong().toString(), // Simple ID generation
            title = title,
            createdAt = 0L // Placeholder as System.currentTimeMillis() is not available in commonMain without expect/actual or libraries
        )
        _uiState.update { currentState ->
            currentState.copy(items = currentState.items + newItem)
        }
    }

    fun archiveItem(item: ToDoItem) {
        _uiState.update { currentState ->
            val updatedItems = currentState.items.map {
                if (it.id == item.id) it.copy(isArchived = true) else it
            }
            currentState.copy(items = updatedItems)
        }
    }
}

data class HomeUiState(
    val items: List<ToDoItem> = emptyList()
)
