package com.example.darknote.repositories

import androidx.lifecycle.LiveData
import com.example.darknote.data.Note

interface NoteRepository {

    suspend fun insertNote(note: Note)

    suspend fun deleteNote(note: Note)

    fun getAllNotes() : LiveData<List<Note>>

}