package com.techuntried.notes.repository

import com.techuntried.notes.database.NoteDao
import com.techuntried.notes.model.NoteEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class NotesRepository @Inject constructor(private val noteDao: NoteDao) {

    suspend fun insertNote(noteEntity: NoteEntity) {
        noteDao.insertNote(noteEntity)
    }

    fun getNotes(): Flow<List<NoteEntity>> {
        return noteDao.getNotes()
    }

    suspend fun getNoteById(id: Long): NoteEntity {
        val result = noteDao.getNoteById(id)
        return result
    }

    suspend fun deleteNote(id: Long) {
        noteDao.deleteNote(id)
    }

    suspend fun updateNote(noteEntity: NoteEntity) {
        noteDao.updateNote(noteEntity)
    }

}

