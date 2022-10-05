package com.tam.simplenotes.data

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteRepository @Inject constructor(
    noteDatabase: NoteDatabase
) {
    private val noteDao = noteDatabase.noteDao

    suspend fun insertNote(note: Note) = noteDao.insertNote(note)

    suspend fun getNote(noteId: Int) = noteDao.getNote(noteId)

    suspend fun getNotes() = noteDao.getNotes()

    suspend fun updateNote(note: Note) = noteDao.updateNote(note)

    suspend fun deleteNote(noteId: Int) = noteDao.deleteNote(noteId)

}