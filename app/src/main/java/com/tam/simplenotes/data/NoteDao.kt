package com.tam.simplenotes.data

import androidx.room.*

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Query("SELECT * FROM table_notes WHERE id = :noteId")
    suspend fun getNote(noteId: Int): Note

    @Query("SELECT * FROM table_notes")
    suspend fun getNotes(): List<Note>

    @Update
    suspend fun updateNote(note: Note)

    @Query("DELETE FROM table_notes WHERE id = :noteId")
    suspend fun deleteNote(noteId: Int)

}