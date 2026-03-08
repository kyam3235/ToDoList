package jp.kyamlab.todolist.di

import jp.kyamlab.todolist.data.ToDoRepository
import jp.kyamlab.todolist.ui.archive.ArchiveViewModel
import jp.kyamlab.todolist.ui.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { ToDoRepository() }
    viewModel { HomeViewModel(get()) }
    viewModel { ArchiveViewModel(get()) }
}
