package com.example.minimalnotes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.minimalnotes.MainApplication
import com.example.minimalnotes.data.Note
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NoteViewModel : ViewModel() {

    private val noteDao = MainApplication.todoDatabase.getNoteDao()

    private val _noteUiState = MutableStateFlow(NoteUiState())
    val noteUIState = _noteUiState.asStateFlow()

    init {
        getAllNotes()
        getFavoriteNotes()
    }

    private fun getAllNotes () {
        viewModelScope.launch {
            _noteUiState.value = _noteUiState.value.copy(
                isLoading = true,
            )

            try {
                noteDao.getAllNotes().collect { notes ->
                    _noteUiState.value = _noteUiState.value.copy(
                        notes = notes,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {

                _noteUiState.value = _noteUiState.value.copy(
                    isLoading = false,
                    errorMessage = "Error on Loading Notes"
                )
            }
        }
    }

    private fun getFavoriteNotes () {
        viewModelScope.launch {
            _noteUiState.value = _noteUiState.value.copy(
                isLoadingFavorites = true,
            )

            try {
                noteDao.getFavoriteNotes().collect { favNotes ->
                    _noteUiState.value = _noteUiState.value.copy(
                        favoriteNotes = favNotes,
                        isLoadingFavorites = false
                    )
                }
            } catch (e: Exception) {
                _noteUiState.value = _noteUiState.value.copy(
                    isLoadingFavorites = false,
                    errorMessage = "Error on Loading Favorite Notes"
                )
            }
        }
    }

    fun insertNote (note: Note) {
        viewModelScope.launch {
            noteDao.insertNote(note)
        }
    }

    fun deleteNote (noteId: Int) {
        viewModelScope.launch {
            noteDao.deleteNoteById(noteId)
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch {
            noteDao.updateNote(note.copy(
                isFavorite = !note.isFavorite!!
            ))
        }
    }

    suspend fun getNoteById (noteId: Int): Note? {
        return noteDao.getNoteById(noteId)
    }
}