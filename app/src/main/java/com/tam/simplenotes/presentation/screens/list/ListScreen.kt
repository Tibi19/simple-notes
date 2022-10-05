package com.tam.simplenotes.presentation.screens.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.core.view.accessibility.AccessibilityEventCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.skydoves.landscapist.glide.GlideImage
import com.tam.simplenotes.data.Note
import com.tam.simplenotes.ui.theme.*

const val NOTE_IMAGE_URL = "https://cdn.allthings.how/wp-content/uploads/2021/07/allthings.how-how-to-use-sticky-notes-in-windows-11-sticky-note.png"

@Composable
fun ListScreen(
    goToNoteScreen: (noteId: Int) -> Unit,
    listVM: ListVM = hiltViewModel()
) {

    val notes = listVM.notes
    val isAddDialogOpenState: MutableState<Boolean> = remember { mutableStateOf(false) }

    LazyColumn {

        item() {
            GlideImage(
                imageModel = NOTE_IMAGE_URL
            )
        }

        items(notes) { note ->

            NoteItem(
                note = note,
                listVM = listVM,
                goToNoteScreen = goToNoteScreen
            )

            Divider(
                color = Color.Black,
                thickness = SIZE_DIVIDER_THICKNESS
            )

        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        FloatingActionButton(
            onClick = { isAddDialogOpenState.value = true },
            modifier = Modifier
                .padding(bottom = PADDING_LARGE, end = PADDING_LARGE)
                .align(Alignment.BottomEnd)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = CONTENT_ADD
            )
        }
    }

    if(isAddDialogOpenState.value) {
        AddNoteDialog(isDialogOpenState = isAddDialogOpenState) { note ->
            listVM.addNote(note)
        }
    }

}

@Composable
fun NoteItem(
    note: Note,
    listVM: ListVM,
    goToNoteScreen: (noteId: Int) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = PADDING_SMALL, horizontal = PADDING_DEFAULT)
            .clickable { goToNoteScreen(note.id) }
    ) {
        Text(
            text = note.title,
            modifier = Modifier.align(Alignment.TopStart),
            style = MaterialTheme.typography.h3
        )

        Text(
            text = note.text,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.body2,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(end = PADDING_NOTE_TEXT_END)
        )

        IconButton(
            onClick = { listVM.deleteNote(note.id) },
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = CONTENT_DELETE,
                tint = MaterialTheme.colors.primary,
            )
        }

    }
}

