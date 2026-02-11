package jp.kyamlab.todolist

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform