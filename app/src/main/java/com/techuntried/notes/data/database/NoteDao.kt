package com.techuntried.notes.data.database

import com.techuntried.notes.data.model.NoteEntity
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
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
    @Update
    suspend fun updateNote(noteEntity: NoteEntity)

}

