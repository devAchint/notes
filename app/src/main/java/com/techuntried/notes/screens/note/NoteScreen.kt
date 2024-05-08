package com.techuntried.notes.screens.note

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.techuntried.notes.model.NoteEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(popBackStack: () -> Boolean) {
    val viewModel: NoteViewModel = hiltViewModel()
    var isNewNote by remember {
        mutableStateOf(true)
    }
    val note = viewModel.note.collectAsState()
    var noteDes by remember {
        mutableStateOf("")
    }
    if (note.value != null) {
        isNewNote = false
        LaunchedEffect(key1 = Unit) {
            noteDes = note.value!!.title
        }
    }
    Scaffold(topBar = {
        NoteScreenToolbar {
            viewModel.deleteNote()
            popBackStack()
        }
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .border(1.dp, color = Color.Black),

                value = noteDes,
                onValueChange = {
                    noteDes = it
                })

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .clip(RoundedCornerShape(12.dp)),
                onClick = {
                    if (isNewNote) {
                        viewModel.insertNote(NoteEntity(0, noteDes))
                        popBackStack()
                    }
                }) {
                val buttonTExt = if (isNewNote) "Add Note" else "Update Note"
                Text(text = buttonTExt)
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreenToolbar(deleteNote: () -> Unit) {
    val context = LocalContext.current
    TopAppBar(
        title = { Text(text = "Notes") },
        actions = {

            IconButton(
                onClick = {
                    deleteNote()
                })
            {
                Icon(Icons.Default.Delete, "")
            }

        }
    )
}

