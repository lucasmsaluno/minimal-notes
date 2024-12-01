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

    @Query("SELECT * FROM notes_table WHERE note_favorite = 1")
    fun getFavoriteNotes(): Flow<List<Note>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote (note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNote (note: Note)

    @Query("DELETE FROM notes_table WHERE id = :noteId")
    suspend fun deleteNoteById(noteId: Int)

    @Query("SELECT * FROM notes_table WHERE id = :noteId")
    suspend fun getNoteById(noteId: Int): Note?
}