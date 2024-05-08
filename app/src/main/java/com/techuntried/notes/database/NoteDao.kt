package com.techuntried.notes.database

import androidx.room.Delete
import com.techuntried.notes.model.NoteEntity
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDao {
    @Insert
    suspend fun insertNote(noteEntity: NoteEntity): Long

    @Query("DELETE FROM notes WHERE id =:id")
    suspend fun deleteNote(id: Long)

    @Query("SELECT * FROM notes")
    fun getNotes(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM notes where id=:id")
    suspend fun getNoteById(id: Long): NoteEntity

}

