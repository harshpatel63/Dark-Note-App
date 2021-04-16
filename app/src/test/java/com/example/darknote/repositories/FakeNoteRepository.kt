package com.example.darknote.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.darknote.data.Note

class FakeNoteRepository : NoteRepository {

    private val notes = mutableListOf<Note>()

    private val allNotes = MutableLiveData<List<Note>>(notes)

    private fun refreshLiveData() {
        allNotes.postValue(notes)
    }

    override suspend fun insertNote(note: Note) {
        notes.add(note)
        refreshLiveData()
    }

    override suspend fun deleteNote(note: Note) {
        notes.remove(note)
        refreshLiveData()
    }

    override fun getAllNotes(): LiveData<List<Note>> {
        return allNotes
    }

}