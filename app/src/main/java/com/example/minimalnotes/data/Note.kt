package com.example.minimalnotes.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "note_title")
    val title: String,

    @ColumnInfo(name = "note_description")
    val content: String
)
