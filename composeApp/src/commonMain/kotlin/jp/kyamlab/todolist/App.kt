package jp.kyamlab.todolist

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import jp.kyamlab.todolist.ui.archive.ArchiveScreen
import jp.kyamlab.todolist.ui.home.HomeScreen

private enum class Screen { Home, Archive }

@Composable
@Preview
fun App() {
    var currentScreen by remember { mutableStateOf(Screen.Home) }

    MaterialTheme {
        when (currentScreen) {
            Screen.Home -> HomeScreen(
                onNavigateToArchive = { currentScreen = Screen.Archive },
                onNavigateToDetail = { itemId -> println("Navigate to Detail: $itemId") }
            )
            Screen.Archive -> ArchiveScreen(
                onNavigateBack = { currentScreen = Screen.Home }
            )
        }
    }
}