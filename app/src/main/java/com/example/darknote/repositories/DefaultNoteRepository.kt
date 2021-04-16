package com.example.darknote.repositories

import androidx.lifecycle.LiveData
import com.example.darknote.data.Note
import com.example.darknote.data.NoteDao
import javax.inject.Inject

class DefaultNoteRepository @Inject constructor(
        private val noteDao: NoteDao
) : NoteRepository {
    override suspend fun insertNote(note: Note) {
        noteDao.insert(note)
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.delete(note)
    }

    override fun getAllNotes(): LiveData<List<Note>> {
       return noteDao.getAllNotes()
    }


}