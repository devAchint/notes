package com.techuntried.notes.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Long,
    @ColumnInfo("title")
    val title:String,
    @ColumnInfo("description")
    val description:String,

)
