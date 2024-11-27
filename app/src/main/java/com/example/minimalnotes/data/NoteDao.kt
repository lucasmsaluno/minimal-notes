package com.example.minimalnotes.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes_table")
    fun getAllNotes(): Flow<List<Note>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote (note: Note)

    @Delete
    suspend fun deleteNote (note: Note)

    @Query("SELECT * FROM notes_table WHERE id = :noteId")
    fun getNoteById (noteId: Int): Note?
}