package com.tam.simplenotes.presentation.screens.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.tam.simplenotes.data.Note
import com.tam.simplenotes.ui.theme.*

@Composable
fun AddNoteDialog(
    isDialogOpenState: MutableState<Boolean>,
    addNote: (note: Note) -> Unit
) {
    Dialog(onDismissRequest = { isDialogOpenState.value = false }) {
        
        val newNoteState: MutableState<Note> = remember { 
            val emptyNote = Note(title = "", text = "") 
            mutableStateOf(emptyNote) 
        }

        Card(shape = RoundedCornerShape(RADIUS_DEFAULT)) {
            Column(modifier = Modifier.width(IntrinsicSize.Max)) {
                AddNoteTitle()
                AddNoteBody(newNoteState = newNoteState)
                AddNoteButtons(isDialogOpenState = isDialogOpenState) {
                    addNote(newNoteState.value)
                }
            }
        }

    }
}

@Composable
fun AddNoteTitle() {
    Text(
        text = TEXT_ADD_NOTE_DIALOG_TITLE,
        style = MaterialTheme.typography.h3,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = PADDING_LARGE)
    )
}

@Composable
fun AddNoteBody(newNoteState: MutableState<Note>) {

    Column(modifier = Modifier.padding(horizontal = PADDING_DEFAULT)) {

        TextField(
            value = newNoteState.value.title,
            label = { Text(text = TEXT_NOTE_TITLE) },
            singleLine = true,
            onValueChange = { newNoteState.value = newNoteState.value.copy(title = it) },
            modifier = Modifier.padding(bottom = PADDING_DEFAULT)
        )

        TextField(
            value = newNoteState.value.text,
            label = { Text(text = TEXT_NOTE_BODY) },
            onValueChange = { newNoteState.value = newNoteState.value.copy(text = it) },
            modifier = Modifier.height(HEIGHT_ADD_NOTE_TEXT)
        )

    }

}

@Composable
fun AddNoteButtons(
    isDialogOpenState: MutableState<Boolean>,
    onSubmit: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(PADDING_DEFAULT)
    ) {

        Button(
            modifier = Modifier.padding(PADDING_SMALL),
            onClick = { isDialogOpenState.value = false },
            shape = RoundedCornerShape(RADIUS_DEFAULT)
        ) {
            Text(text = TEXT_CANCEL)
        }

        Button(
            modifier = Modifier.padding(PADDING_SMALL),
            onClick = {
                onSubmit()
                isDialogOpenState.value = false
            },
            shape = RoundedCornerShape(RADIUS_DEFAULT)
        ) {
            Text(text = TEXT_SUBMIT)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun AddNoteDialogPreview() =
    SimpleNotesTheme {
        AddNoteDialog(
            isDialogOpenState = mutableStateOf(true),
            addNote = {}
        )
    }
