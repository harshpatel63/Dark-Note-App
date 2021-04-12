package com.example.darknote.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.darknote.R
import com.example.darknote.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class NoteDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: NoteDatabase
    private lateinit var dao: NoteDao

    @Before
    fun setup(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            NoteDatabase::class.java
        ).allowMainThreadQueries()
            .build()
        dao = database.noteDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertNote() = runBlockingTest {
        val note = Note("Title","dateTime","subtitle","Text", R.color.colorDefaultNoteColor)
        note.id = 1
        dao.insert(note)

        val allNotes = dao.getAllNotes().getOrAwaitValue()

        assertThat(allNotes).contains(note)
    }

    @Test
    fun deleteNote() = runBlockingTest {
        val note = Note("Title","dateTime","subtitle","Text", R.color.colorDefaultNoteColor)
        note.id = 2
        dao.insert(note)
        dao.delete(note)

        val allNotes = dao.getAllNotes().getOrAwaitValue()

        assertThat(allNotes).doesNotContain(note)
    }

}