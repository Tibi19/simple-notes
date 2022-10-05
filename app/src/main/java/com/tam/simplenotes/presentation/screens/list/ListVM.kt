package com.tam.simplenotes.presentation.screens.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tam.simplenotes.data.Note
import com.tam.simplenotes.data.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListVM @Inject constructor(
    private val repository: NoteRepository
): ViewModel() {

    var notes by mutableStateOf(emptyList<Note>())
        private set

    init {
        loadNotes()
    }

    private fun loadNotes() =
        viewModelScope.launch {
            notes = repository.getNotes()
        }

    fun addNote(note: Note) =
        viewModelScope.launch {
            repository.insertNote(note)
            loadNotes()
        }

    fun deleteNote(noteId: Int) =
        viewModelScope.launch {
            repository.deleteNote(noteId)
            loadNotes()
        }

}