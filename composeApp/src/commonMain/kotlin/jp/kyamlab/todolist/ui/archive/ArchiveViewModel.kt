package jp.kyamlab.todolist.ui.archive

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.kyamlab.todolist.data.ToDoRepository
import jp.kyamlab.todolist.model.ToDoItem
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class ArchiveViewModel(private val repository: ToDoRepository) : ViewModel() {

    val uiState: StateFlow<ArchiveUiState> = repository.items
        .map { items -> ArchiveUiState(items = items.filter { it.isArchived }) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ArchiveUiState())

    fun unarchiveItem(id: String) {
        repository.unarchiveItem(id)
    }
}

data class ArchiveUiState(
    val items: List<ToDoItem> = emptyList()
)