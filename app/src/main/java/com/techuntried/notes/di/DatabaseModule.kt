package com.techuntried.notes.di

import android.content.Context
import androidx.room.Room
import com.techuntried.notes.data.database.NotesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule() {

    @Singleton
    @Provides
    fun provideMyAppDb(
        @ApplicationContext context: Context
    ): NotesDatabase {
        return Room.databaseBuilder(context, NotesDatabase::class.java, "myAppDb")
            .build()
    }

    @Provides
    fun provideNoteDao(db: NotesDatabase) = db.noteDao()
}