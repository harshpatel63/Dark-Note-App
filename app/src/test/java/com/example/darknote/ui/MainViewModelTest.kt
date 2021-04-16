package com.example.darknote.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.darknote.MainCoroutineRule
import com.example.darknote.R
import com.example.darknote.repositories.FakeNoteRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        viewModel = MainViewModel(FakeNoteRepository())
    }

    @Test
    fun `insert note with empty field returns error`() {
        val value = viewModel.validateNote("" ,"sadf","afsdf","sdfsd", R.color.colorDefaultNoteColor)
        assertThat(value).isFalse()
    }
}