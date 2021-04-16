package com.example.darknote.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.darknote.R
import com.example.darknote.data.Note
import com.example.darknote.repositories.NoteRepository
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
        private val repository: NoteRepository
) : ViewModel() {

    private val colorList = listOf(R.color.colorDefaultNoteColor,
        R.color.colorNoteColor2,
        R.color.colorNoteColor3,
        R.color.colorNoteColor4,
        R.color.colorNoteColor5)

    val notes = repository.getAllNotes()

    fun insertNote(note: Note) = viewModelScope.launch {
        repository.insertNote(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        repository.deleteNote(note)
    }

    fun validateNote(
            title:String,
            dateTime: String,
            subtitle: String,
            noteText : String,
            color: Int
            ): Boolean{
            if(title.isEmpty() || title == "")
                return false
            if(subtitle.isEmpty() || subtitle == "")
                return false
            if(dateTime.isEmpty() || dateTime == "")
                return false
            if(noteText.isEmpty() || noteText == "")
                return false
        if(!colorList.contains(color))
                return false

        val note = Note(title, dateTime, subtitle, noteText, color)
        insertNote(note)

        return true
    }

}