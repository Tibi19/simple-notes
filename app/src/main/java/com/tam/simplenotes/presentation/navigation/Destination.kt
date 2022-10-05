package com.tam.simplenotes.presentation.navigation

const val ARG_NOTE_ID = "note_id"
const val ARG_OPTIONAL_ID = "optional_id"

sealed class Destination(val route: String) {

    object List: Destination("list")

    object Note: Destination("note/{$ARG_NOTE_ID}") {
        fun createRoute(noteId: Int) = "note/$noteId"
    }

    object OptionalArgument: Destination("optional_arg?$ARG_OPTIONAL_ID={$ARG_OPTIONAL_ID}") {
        fun createRoute(argument: Int) = "optional_arg?$ARG_OPTIONAL_ID=$argument"
        fun createDefaultRoute() = "optional_arg"
    }

}