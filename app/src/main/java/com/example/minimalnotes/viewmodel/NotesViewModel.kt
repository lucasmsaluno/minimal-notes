package com.example.minimalnotes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.minimalnotes.MainApplication
import com.example.minimalnotes.data.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NoteViewModel : ViewModel() {

    val noteDao = MainApplication.todoDatabase.getNoteDao()

    private val _noteUiState = MutableStateFlow(NoteUiState())
    val noteUIState = _noteUiState.asStateFlow()

    init {
        getAllNotes()
    }

    private fun getAllNotes () {
        viewModelScope.launch {
            _noteUiState.value = _noteUiState.value.copy(
                isLoading = true
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

    fun insertNote (note: Note) {
        viewModelScope.launch {
            noteDao.insertNote(note)
        }
    }

    fun deleteNote (note: Note) {
        viewModelScope.launch {
            noteDao.deleteNote(note)
        }
    }

    fun getNoteById(noteId: Int){
        viewModelScope.launch {
            _noteUiState.value = _noteUiState.value.copy(
                selectedNote = _noteUiState.value.notes.find { it.id == noteId }
            )
        }
    }
}