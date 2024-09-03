package com.techuntried.notes.ui.screens.note

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techuntried.notes.data.model.NoteEntity
import com.techuntried.notes.data.repository.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val notesRepository: NotesRepository,
    private val savedStateHandle: SavedStateHandle
) :
    ViewModel() {

    private val _noteId = MutableStateFlow<Long>(-1)
    val noteId: StateFlow<Long>
        get() =
            _noteId


    private val _note = MutableStateFlow<NoteEntity?>(null)
    val note: StateFlow<NoteEntity?>
        get() = _note

    init {
        val id = savedStateHandle.get<Long>("id")
        if (id != null && id != -1L) {
            _noteId.value = id
            getNoteById(noteId.value)
        }
    }

    private fun getNoteById(id: Long) {
        viewModelScope.launch {
            _note.value = notesRepository.getNoteById(id)
        }
    }

    fun insertNote(noteEntity: NoteEntity) {
        viewModelScope.launch {
            notesRepository.insertNote(noteEntity)
        }
    }

    fun deleteNote() {
        viewModelScope.launch {
            if (noteId.value != -1L)
                notesRepository.deleteNote(noteId.value)
        }
    }

    fun updateNote(noteTitle: String, noteDes: String) {
        viewModelScope.launch {
            notesRepository.updateNote(NoteEntity(noteId.value, noteTitle, noteDes))
        }
    }


}