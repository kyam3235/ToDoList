package jp.kyamlab.todolist.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.kyamlab.todolist.data.ToDoRepository
import jp.kyamlab.todolist.model.ToDoItem
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeViewModel : ViewModel() {

    val uiState: StateFlow<HomeUiState> = ToDoRepository.items
        .map { items -> HomeUiState(items = items.filter { !it.isArchived }) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), HomeUiState())

    fun addItem(title: String, description: String = "") {
        ToDoRepository.addItem(title, description)
    }

    fun archiveItem(id: String) {
        ToDoRepository.archiveItem(id)
    }

    fun updateItem(item: ToDoItem) {
        ToDoRepository.updateItem(item)
    }
}

data class HomeUiState(
    val items: List<ToDoItem> = emptyList()
)