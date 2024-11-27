package com.example.minimalnotes.ui.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
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
fun Details (
    noteId: String?,
    navController: NavController,
    noteViewModel: NoteViewModel
) {
    var titleInput by remember { mutableStateOf("") }
    var contentInput by remember { mutableStateOf("") }

    Scaffold (
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = Color(0xFF4B4A4B),
                ),
                title = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = if (noteId == null) "Editing Note" else "Creating New Note")
                    }
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = "Back",
                        modifier = Modifier
                                .padding(start = 8.dp)
                                .clickable {
                                    navController.popBackStack()
                                }
                    )
                },
                actions = {
                    Spacer(modifier = Modifier.width(20.dp))
                }
            )
        },
        floatingActionButton = {
            androidx.compose.material3.FloatingActionButton(
                onClick = {
                    noteViewModel.insertNote(Note(title = titleInput, content = contentInput))
                    navController.popBackStack()
                },
                containerColor = Color(0xFFDeDFDE),
                contentColor = MaterialTheme.colorScheme.secondary
            ) {
                Icon(
                    imageVector = if (noteId == null)Icons.Filled.Add else Icons.Filled.Create,
                    "Small floating action button."
                )
            }
        }
    ) { innerPadding ->
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = innerPadding.calculateTopPadding(), start = 20.dp, end = 20.dp)
                .fillMaxSize()
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("Note Title")
                },
                value = titleInput,
                onValueChange = { titleInput = it }
            )
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("Note Content")
                },
                value = contentInput,
                onValueChange = { contentInput = it },
                maxLines = 20
            )
        }
    }
}