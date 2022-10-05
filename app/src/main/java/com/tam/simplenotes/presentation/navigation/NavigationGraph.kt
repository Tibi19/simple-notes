package com.tam.simplenotes.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tam.simplenotes.presentation.screens.list.ListScreen
import com.tam.simplenotes.presentation.screens.note.NoteScreen

@Composable
fun NavigationGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Destination.List.route
    ) {

        composable(route = Destination.List.route) {

            val goToNoteScreenLambda: (noteId: Int) -> Unit = { noteId ->
                val noteRoute = Destination.Note.createRoute(noteId)
                navController.navigate(noteRoute)
            }

            ListScreen(goToNoteScreen = goToNoteScreenLambda)
        }

        composable(
            route = Destination.Note.route,
            arguments = listOf(
                navArgument(ARG_NOTE_ID) { type = NavType.IntType }
            )
        ) {
            NoteScreen()
        }
        
    }
}