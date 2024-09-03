package com.techuntried.notes.ui.nav


sealed class Screens(val route: String) {
    object HomeScreen : Screens("home")
    object NoteScreen : Screens("note?id={id}") {
        fun passId(id: Long): String {
            return "note?id=$id"
        }
    }
}
