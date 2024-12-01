package com.example.minimalnotes

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.minimalnotes.ui.navigation.AppNavigation
import com.example.minimalnotes.ui.navigation.BottomNavigationBar
import com.example.minimalnotes.ui.navigation.getBottomNavigationItems
import com.example.minimalnotes.ui.theme.MinimalNotesTheme
import com.example.minimalnotes.viewmodel.NoteViewModel

class MainActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val noteViewModel by viewModels<NoteViewModel>()

            MinimalNotesTheme (darkTheme = false) {
                MainScreen(
                    noteViewModel = noteViewModel
                )
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen (noteViewModel: NoteViewModel) {
    val navController = rememberNavController()
    val items = getBottomNavigationItems()
    val selectedItemIndex = rememberSaveable { mutableIntStateOf(0) }

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    val isBottomBarVisible = currentRoute != "note_details/{noteId}"
    val isFloatingActionButtonVisible = currentRoute == "Notes"

    Scaffold(
        bottomBar = {
            if (isBottomBarVisible) {
                BottomNavigationBar(
                    navController = navController,
                    items = items,
                    selectedItemIndex = selectedItemIndex
                )
            }
        },
        floatingActionButton = {
            if (isFloatingActionButtonVisible) {
                FloatingActionButton(
                    onClick = {
                        navController.navigate("note_details/")
                    },
                    containerColor = Color(0xFFDeDFDE),
                    contentColor = MaterialTheme.colorScheme.secondary
                ) {
                    Icon(Icons.Filled.Add, "Small floating action button.")
                }
            }
        }
    ) {
        AppNavigation(
            navController = navController,
            noteViewModel = noteViewModel
        )
    }
}
