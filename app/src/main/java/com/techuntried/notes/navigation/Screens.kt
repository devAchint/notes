package com.techuntried.notes.navigation

import androidx.navigation.NavType
import com.techuntried.notes.screens.note.NoteScreen


sealed class Screens(val route: String) {
    object HomeScreen : Screens("home")
    object NoteScreen : Screens("note?id={id}") {
        fun passId(id: Long): String {
            return "note?id=$id"
        }
    }
}
