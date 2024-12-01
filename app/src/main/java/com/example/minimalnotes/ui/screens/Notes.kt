package com.example.minimalnotes.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.minimalnotes.ui.components.NoteCard
import com.example.minimalnotes.ui.theme.playfairFontFamily
import com.example.minimalnotes.viewmodel.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
@Composable
fun Notes(
    navController: NavController,
    modifier: Modifier = Modifier,
    noteViewModel: NoteViewModel
) {
    val state = noteViewModel.noteUIState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = MaterialTheme.colorScheme.secondary,
                ),
                title = {
                    Row {
                        Text(
                            "Notes",
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = playfairFontFamily,
                            fontSize = 32.sp
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("note_details/")
                },
                containerColor = Color(0xFFDeDFDE),
                contentColor = MaterialTheme.colorScheme.secondary
            ) {
                Icon(Icons.Filled.Add, "Floating action button.")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color(0xFFDeDFDE))
                .padding(top = innerPadding.calculateTopPadding(), start = 16.dp, end = 16.dp, bottom = 100.dp)
        ) {
            if (state.value.isLoading) {
                Text("Loading...", fontSize = 20.sp, color = Color.Gray)


            } else if (state.value.errorMessage != null) {
                Text("Error: ${state.value.errorMessage}", fontSize = 20.sp, color = Color.Red)

            } else {
                LazyColumn {
                    items(state.value.notes) { note ->
                        NoteCard(
                            note = note,
                            onNoteClick = {
                                navController.navigate("note_details/${note.id}")
                            }
                        )
                    }
                }
            }
        }
    }
}



