package com.example.darknote.di

import android.content.Context
import androidx.room.Room
import com.example.darknote.data.NoteDao
import com.example.darknote.data.NoteDatabase
import com.example.darknote.repositories.DefaultNoteRepository
import com.example.darknote.repositories.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideNoteDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, NoteDatabase::class.java, "note_db").build()

    @Singleton
    @Provides
    fun provideNoteDao(
        database: NoteDatabase
    ) = database.noteDao()

    @Singleton
    @Provides
    fun providesDefaultNoteRepository(
            dao: NoteDao
    ) = DefaultNoteRepository(dao) as NoteRepository

}