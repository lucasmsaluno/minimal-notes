package com.example.minimalnotes.viewmodel

import com.example.minimalnotes.data.Note

data class NoteUiState(
    val notes: List<Note> = emptyList(),
    val isLoading: Boolean = false,

    val favoriteNotes: List<Note> = emptyList(),
    val isLoadingFavorites: Boolean = false,

    val errorMessage: String? = null,
)