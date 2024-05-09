package com.techuntried.notes.screens.note

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
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
    var noteTitle by remember {
        mutableStateOf("")
    }
    var noteDes by remember {
        mutableStateOf("")
    }


    if (note.value != null) {
        LaunchedEffect(key1 = Unit) {
            isNewNote = false
            noteDes = note.value!!.description
            noteTitle = note.value!!.title
        }
    }
    Scaffold(topBar = {
        NoteScreenToolbar(isNewNote, popBackStack = { popBackStack() }) {
            viewModel.deleteNote()
            popBackStack()
        }
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                placeholder = { Text(text = "Title here") },
                shape = RoundedCornerShape(10.dp),
                value = noteTitle,
                onValueChange = {
                    noteTitle = it
                },
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    containerColor = MaterialTheme.colorScheme.tertiary
                )
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(bottom = 8.dp)
                    .weight(1f),
                placeholder = { Text(text = "Note here") },
                shape = RoundedCornerShape(10.dp),
                value = noteDes,
                onValueChange = {
                    noteDes = it
                },
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    containerColor = MaterialTheme.colorScheme.tertiary
                )
            )
            val context = LocalContext.current
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colorScheme.secondary,
                    containerColor = MaterialTheme.colorScheme.primary
                )
                ,
                onClick = {
                    if (noteDes.isBlank() && noteTitle.isBlank()) {
                        Toast.makeText(context, "empty note", Toast.LENGTH_SHORT).show()
                    } else {
                        if (isNewNote) {
                            viewModel.insertNote(NoteEntity(0, noteTitle, noteDes))
                        } else {
                            viewModel.updateNote(noteTitle, noteDes)
                        }
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
fun NoteScreenToolbar(
    isNewNote: Boolean,
    popBackStack: () -> Unit,
    deleteNote: () -> Unit
) {
    TopAppBar(
        title = { Text(text = "Notes") },
        colors= TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        navigationIcon = {
            IconButton(
                onClick = {
                    popBackStack()
                }
            ) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, "")
            }
        },
        actions = {
            if (!isNewNote)
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

