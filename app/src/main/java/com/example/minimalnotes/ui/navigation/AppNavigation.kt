package com.example.minimalnotes.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.minimalnotes.ui.screens.Details
import com.example.minimalnotes.ui.screens.Favorites
import com.example.minimalnotes.ui.screens.Notes
import com.example.minimalnotes.viewmodel.NoteViewModel

@Composable
fun AppNavigation(
    navController: NavHostController,
    noteViewModel: NoteViewModel
) {
    NavHost(navController = navController, startDestination = "Notes") {
        composable(route = "Notes") {
            Notes(
                navController = navController,
                noteViewModel = noteViewModel
            )
        }
        composable(route = "Favorites") {
            Favorites(
                navController = navController,
                noteViewModel = noteViewModel
            )
        }
        composable(route = "note_details/{noteId}") { backStackEntry ->
            val noteId = backStackEntry.arguments?.getString("noteId") ?: "Unknown"
            Details(navController = navController, noteId = noteId, noteViewModel = noteViewModel)
        }
    }
}