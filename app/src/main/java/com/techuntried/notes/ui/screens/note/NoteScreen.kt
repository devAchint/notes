package com.techuntried.notes.ui.screens.note

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.techuntried.notes.data.model.NoteEntity
import com.techuntried.notes.ui.components.NoteInput

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(popBackStack: () -> Boolean) {
    val viewModel: NoteViewModel = hiltViewModel()
    val context = LocalContext.current
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

    val isFieldEmpty = remember {
        derivedStateOf {
            noteTitle.isBlank() && noteDes.isBlank()
        }
    }

    note.value?.let {
        LaunchedEffect(key1 = Unit) {
            isNewNote = false
            noteDes = it.description
            noteTitle = it.title
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
            NoteInput(
                text = noteTitle,
                onTextChange = { noteTitle = it },
                placeHolder = "Title here",
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            NoteInput(
                text = noteDes,
                onTextChange = { noteDes = it },
                placeHolder = "Note here",
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(10.dp),
                onClick = {
                    if (isFieldEmpty.value) {
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
                val buttonText = if (isNewNote) "Add Note" else "Update Note"
                Text(text = buttonText, color = Color.White)
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
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        navigationIcon = {
            IconButton(onClick = { popBackStack() })
            {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, "")
            }
        },
        actions = {
            if (!isNewNote) {
                IconButton(onClick = { deleteNote() })
                {
                    Icon(Icons.Default.Delete, "")
                }
            }
        }
    )
}

