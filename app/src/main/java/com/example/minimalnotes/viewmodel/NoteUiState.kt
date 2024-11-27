package com.example.minimalnotes.viewmodel

import com.example.minimalnotes.data.Note

data class NoteUiState(
    val notes: List<Note> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val selectedNote: Note? = null
)