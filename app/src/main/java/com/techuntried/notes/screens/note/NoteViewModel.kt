package com.techuntried.notes.screens.note

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techuntried.notes.model.NoteEntity
import com.techuntried.notes.repository.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val notesRepository: NotesRepository,
    private val savedStateHandle: SavedStateHandle
) :
    ViewModel() {

    private val _note = MutableStateFlow<NoteEntity?>(null)
    val note: StateFlow<NoteEntity?>
        get() =
            _note

    init {
        val id = savedStateHandle.get<Long>("id")
        if (id != null && id != -1L) {
            getNoteById(id)
        }
    }

    fun getNoteById(id: Long) {
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
        val id = savedStateHandle.get<Long>("id")
        if (id != null)
            viewModelScope.launch {
                notesRepository.deleteNote(id)
            }
    }


}