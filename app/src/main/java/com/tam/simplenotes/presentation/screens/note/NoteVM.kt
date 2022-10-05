package com.tam.simplenotes.presentation.screens.note

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tam.simplenotes.data.Note
import com.tam.simplenotes.data.NoteRepository
import com.tam.simplenotes.presentation.navigation.ARG_NOTE_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteVM @Inject constructor(
    private val repository: NoteRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    var note: Note? by mutableStateOf(Note(title = "Title", text = "Text"))
        private set

    init {
        loadNote()
    }

    private fun loadNote() =
        viewModelScope.launch {
            val noteId = savedStateHandle.get<Int>(ARG_NOTE_ID) ?: return@launch
            note = repository.getNote(noteId)
        }

    fun saveNote() =
        viewModelScope.launch {
            note?.let { repository.updateNote(it) }
            loadNote()
        }

    fun updateText(newText: String) {
        note = note?.copy(text = newText)
    }

    fun updateTitle(newTitle: String) {
        note = note?.copy(title = newTitle)
    }

}