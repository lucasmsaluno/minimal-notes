package com.example.minimalnotes.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase: RoomDatabase() {
    companion object {
        const val NAME = "Notes_DB"
    }

    abstract fun getNoteDao(): NoteDao
}