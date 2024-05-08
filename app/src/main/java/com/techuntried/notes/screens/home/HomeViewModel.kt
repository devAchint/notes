package com.techuntried.notes.screens.home

import android.util.Log
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
class HomeViewModel @Inject constructor(private val notesRepository: NotesRepository) :
    ViewModel() {

    private val _notes = MutableStateFlow<List<NoteEntity>>(emptyList())
    val notes: StateFlow<List<NoteEntity>>
        get() = _notes

    init {
        getNotes()
    }
    fun getNotes() {
        viewModelScope.launch {
             notesRepository.getNotes().collect{
                 Log.d("MYDEBUG", "${it.size}")
                _notes.value=it
            }
        }
    }

}