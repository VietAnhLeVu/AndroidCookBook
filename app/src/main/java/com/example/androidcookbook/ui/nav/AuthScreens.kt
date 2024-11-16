package com.example.androidcookbook.ui.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.androidcookbook.ui.screen.signinandup.SignBackground
import com.example.androidcookbook.ui.viewmodel.SignViewModel

/**
 * Login, registration, forgot password screens nav graph builder
 * (Unauthenticated user)
 */
fun NavGraphBuilder.authScreens(viewModel: SignViewModel,navController: NavController) {
    navigation(
        route = NavigationRoutes.AuthScreens.NavigationRoute.route,
        startDestination = NavigationRoutes.AuthScreens.Login.route
    ) {
        composable(route = NavigationRoutes.AuthScreens.Login.route) {
            SignBackground(
                viewModel = viewModel,
                isSignIn = false,
                isOpenDialog = false,
            )
        }
    }
}