package com.example.androidcookbook.ui.nav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.example.androidcookbook.ui.screen.auth.AuthViewModel

@Composable
fun authViewModel(
    backStackEntry: NavBackStackEntry,
    navController: NavController,
): AuthViewModel {
    val parentEntry = remember(backStackEntry) {
        navController.getBackStackEntry(NavigationRoutes.AuthScreens.NavigationRoute.route)
    }
    val authViewModel = hiltViewModel<AuthViewModel>(parentEntry)
    return authViewModel
}