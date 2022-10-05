package com.tam.simplenotes.presentation.screens.note

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.tam.simplenotes.ui.theme.*

@Composable
fun NoteScreen(noteVM: NoteVM = hiltViewModel()) {

    val note = noteVM.note ?: return

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextField(
            value = note.title,
            onValueChange = { noteVM.updateTitle(it) },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            textStyle = MaterialTheme.typography.h2,
            modifier = Modifier.padding(vertical = PADDING_LARGE)
        )

        TextField(
            value = note.text,
            onValueChange = { noteVM.updateText(it) },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = FRACTION_NOTE_TEXT_OF_SCREEN)
                .padding(vertical = PADDING_DEFAULT)
        )

        val context = LocalContext.current
        Button(onClick = {
            noteVM.saveNote()
            Toast.makeText(context, TEXT_NOTE_SAVED, Toast.LENGTH_SHORT).show()
        }) {
            Text(text = TEXT_SAVE_NOTE)
        }

    }

}