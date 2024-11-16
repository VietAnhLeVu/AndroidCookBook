package com.example.androidcookbook.ui.nav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.androidcookbook.ui.screen.auth.ForgotPasswordScreen
import com.example.androidcookbook.ui.screen.auth.AuthViewModel
import com.example.androidcookbook.ui.screen.auth.LoginScreen
import com.example.androidcookbook.ui.screen.auth.RegisterScreen

/**
 * Login, registration, forgot password screens nav graph builder
 * (Unauthenticated user)
 */
fun NavGraphBuilder.authScreens(authViewModel: AuthViewModel, navController: NavController) {
    navigation(
        route = NavigationRoutes.AuthScreens.NavigationRoute.route,
        startDestination = NavigationRoutes.AuthScreens.Login.route
    ) {
        composable(route = NavigationRoutes.AuthScreens.Login.route) {
            LoginScreen(
                authViewModel = authViewModel,
                onNavigateToSignUp = {
                    navController.navigate(NavigationRoutes.AuthScreens.Register.route)
                },
                onForgotPasswordClick = {},
            )
        }
        composable(route = NavigationRoutes.AuthScreens.Register.route) {
            RegisterScreen(
                authViewModel = authViewModel,
                onNavigateToSignIn = {
                    navController.navigate(NavigationRoutes.AuthScreens.Login.route)
                }
            )
        }

        composable(route = NavigationRoutes.AuthScreens.ForgotPassword.route) {
            ForgotPasswordScreen(

            )
        }
    }
}

