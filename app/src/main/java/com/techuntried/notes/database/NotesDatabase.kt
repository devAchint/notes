package com.techuntried.notes.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.techuntried.notes.model.NoteEntity

@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}

