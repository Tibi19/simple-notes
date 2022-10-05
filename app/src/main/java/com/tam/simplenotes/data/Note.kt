package com.tam.simplenotes.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val text: String
)
