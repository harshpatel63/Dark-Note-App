package com.example.darknote.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.darknote.data.Note
import com.example.darknote.repositories.NoteRepository
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
        private val repository: NoteRepository
) : ViewModel() {

    val notes = repository.getAllNotes()

    fun insertNote(note: Note) = viewModelScope.launch {
        repository.insertNote(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        repository.deleteNote(note)
    }

}