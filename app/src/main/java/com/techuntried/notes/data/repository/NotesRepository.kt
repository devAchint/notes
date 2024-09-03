package com.techuntried.notes.data.repository

import com.techuntried.notes.data.database.NoteDao
import com.techuntried.notes.data.model.NoteEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NotesRepository @Inject constructor(private val noteDao: NoteDao) {

    suspend fun insertNote(noteEntity: NoteEntity) {
        withContext(Dispatchers.IO) {
            noteDao.insertNote(noteEntity)
        }
    }

    fun getNotes(): Flow<List<NoteEntity>> {
        return noteDao.getNotes().flowOn(Dispatchers.IO)
    }

    suspend fun getNoteById(id: Long): NoteEntity {
        return withContext(Dispatchers.IO) {
            noteDao.getNoteById(id)
        }
    }

    suspend fun deleteNote(id: Long) {
        withContext(Dispatchers.IO) {
            noteDao.deleteNote(id)
        }
    }

    suspend fun updateNote(noteEntity: NoteEntity) {
        withContext(Dispatchers.IO) {
            noteDao.updateNote(noteEntity)
        }
    }

}

