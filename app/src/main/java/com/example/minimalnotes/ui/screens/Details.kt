package com.example.minimalnotes.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.minimalnotes.data.Note
import com.example.minimalnotes.viewmodel.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Details(
    noteId: String? = null,
    navController: NavController,
    noteViewModel: NoteViewModel
) {
    var titleInput by remember { mutableStateOf("") }
    var contentInput by remember { mutableStateOf("") }
    var isLoaded by remember { mutableStateOf(false) }

    var isFavorite by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var noteHolder by remember { mutableStateOf(Note(id = 0, title = "", content = "", isFavorite = false)) }

    LaunchedEffect(noteId) {
        val id = noteId?.toIntOrNull()
        if (id != null) {
            val note = noteViewModel.getNoteById(id)

            if (note != null) {
                noteHolder = note
                titleInput = note.title
                contentInput = note.content
                isFavorite = note.isFavorite == true
            }
        }
        isLoaded = true
    }

    if (!isLoaded) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Loading...")
        }
        return
    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = MaterialTheme.colorScheme.secondary
                ),
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        if (noteId.isNullOrEmpty()) {
                            Text(text = "Creating New Note", modifier = Modifier.padding(end = 30.dp))
                        } else {
                            Text(text = "Editing Note", modifier = Modifier.padding(start = 50.dp))
                        }

                    }
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = "Back",
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .clickable { navController.popBackStack() }
                    )
                },
                actions = {
                    if (!noteId.isNullOrEmpty()) {
                        Icon(
                            imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                            contentDescription = if (isFavorite) "Remove from Favorites" else "Add to Favorites",
                            modifier = Modifier
                                .padding(8.dp)
                                .clickable {
                                    isFavorite = !isFavorite
                                    noteViewModel.updateNote(noteHolder)
                                }
                        )

                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete Note",
                            modifier = Modifier
                                .padding(8.dp)
                                .clickable {
                                    showDeleteDialog = true
                                }
                        )

                        if (showDeleteDialog) {
                            androidx.compose.material3.AlertDialog(
                                onDismissRequest = { showDeleteDialog = false },
                                title = { Text("Delete Note") },
                                text = { Text("Are you sure you want to delete this note?") },
                                confirmButton = {
                                    androidx.compose.material3.TextButton(
                                        onClick = {
                                            noteId?.toIntOrNull()?.let { id ->
                                                noteViewModel.deleteNote(id)
                                                navController.popBackStack()
                                            }
                                            showDeleteDialog = false
                                        }
                                    ) {
                                        Text("Delete")
                                    }
                                },
                                dismissButton = {
                                    androidx.compose.material3.TextButton(
                                        onClick = {
                                            showDeleteDialog = false
                                        }
                                    ) {
                                        Text("Cancel")
                                    }
                                }
                            )
                        }
                    }
                }
            )

        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    noteViewModel.insertNote(
                        Note(
                            id = noteId?.toIntOrNull() ?: 0,
                            title = titleInput,
                            content = contentInput,
                            isFavorite = false
                        )
                    )
                    navController.popBackStack()
                },
                containerColor = Color(0xFFDeDFDE),
                contentColor = MaterialTheme.colorScheme.secondary
            ) {
                Icon(
                    imageVector = if (noteId.isNullOrEmpty()) Icons.Filled.Add else Icons.Filled.Create,
                    contentDescription = "Save Note"
                )
            }
        }
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = innerPadding.calculateTopPadding(), start = 20.dp, end = 20.dp)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Note Title") },
                value = titleInput,
                onValueChange = { titleInput = it }
            )
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Note Content") },
                value = contentInput,
                onValueChange = { contentInput = it },
                maxLines = 20
            )
        }
    }
}

