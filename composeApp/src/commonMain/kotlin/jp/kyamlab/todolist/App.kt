package jp.kyamlab.todolist

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import jp.kyamlab.todolist.ui.home.HomeScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
        HomeScreen(
            onNavigateToArchive = {
                println("Navigate to Archive")
            },
            onNavigateToDetail = { itemId ->
                println("Navigate to Detail: $itemId")
            }
        )
    }
}