# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## 言語設定

ユーザーとのやりとりは常に**日本語**で行うこと。

## Project Overview

This is a **Kotlin Multiplatform (KMP)** project targeting Android and iOS, using **Compose Multiplatform** for a shared UI layer. The app is a simple ToDo list manager.

## Build Commands

```bash
# Build Android debug APK
./gradlew :composeApp:assembleDebug

# Run common tests
./gradlew :composeApp:allTests

# Run a single test class
./gradlew :composeApp:testDebugUnitTest --tests "jp.kyamlab.todolist.ComposeAppCommonTest"
```

For iOS: open `iosApp/iosApp.xcodeproj` in Xcode and run from there.

## Architecture

The shared code lives in `composeApp/src/commonMain/` and follows an MVVM pattern:

- **`App.kt`** — Root composable entry point. Currently renders `HomeScreen` directly (navigation is stubbed with `println`).
- **`model/ToDoItem.kt`** — Single data model: `id`, `title`, `description`, `isArchived`, `createdAt`.
- **`ui/home/HomeViewModel.kt`** — Holds `HomeUiState` (a list of `ToDoItem`s) in a `StateFlow`. State is in-memory only (no persistence). Item IDs are generated with `kotlin.random.Random`.
- **`ui/home/HomeScreen.kt`** — All UI for the home screen: list with swipe-to-archive, add dialog, and edit bottom sheet.

Platform entry points:
- Android: `composeApp/src/androidMain/.../MainActivity.kt`
- iOS: `composeApp/src/iosMain/.../MainViewController.kt` (called from `iosApp/`)

## Key Constraints

- **No persistence**: Data lives only in `HomeViewModel` in-memory state. There is no database or file storage yet.
- **No navigation library**: Navigation callbacks (`onNavigateToArchive`, `onNavigateToDetail`) are passed as lambdas but not yet implemented beyond `println`.
- **`System.currentTimeMillis()` unavailable in commonMain**: `createdAt` is hardcoded to `0L` as a workaround. Use `expect`/`actual` or a multiplatform library if real timestamps are needed.
- **Compose resources** (`Res.string.*`) are defined in `composeApp/src/commonMain/composeResources/` and referenced via the generated `todolist.composeapp.generated.resources` package.