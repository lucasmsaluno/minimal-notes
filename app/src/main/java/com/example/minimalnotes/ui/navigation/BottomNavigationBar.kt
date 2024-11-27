package com.example.minimalnotes.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController

data class BottomNavigationItem (
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

@Composable
fun BottomNavigationBar(
    navController: NavController,
    items: List<BottomNavigationItem>,
    selectedItemIndex: MutableState<Int>
) {
    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                label = { Text(text = item.title) },
                selected = selectedItemIndex.value == index,
                onClick = {
                    selectedItemIndex.value = index
                    navController.navigate(item.title)
                },
                icon = {
                    Icon(
                        imageVector = if (index == selectedItemIndex.value) item.selectedIcon else item.unselectedIcon,
                        contentDescription = item.title
                    )
                },
                alwaysShowLabel = false
            )
        }
    }
}

fun getBottomNavigationItems(): List<BottomNavigationItem> {
    return listOf(
        BottomNavigationItem(
            title = "Notes",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home
        ),
        BottomNavigationItem(
            title = "Favorites",
            selectedIcon = Icons.Filled.Favorite,
            unselectedIcon = Icons.Outlined.FavoriteBorder
        ),
        BottomNavigationItem(
            title = "Settings",
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings
        )
    )
}
