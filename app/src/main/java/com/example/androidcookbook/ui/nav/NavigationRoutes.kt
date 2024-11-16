package com.example.androidcookbook.ui.nav

import com.example.androidcookbook.ui.nav.NavigationRoutes.AppScreens

/**
 * Sealed class storing all navigation routes
 */
sealed class NavigationRoutes {

    // Unauthenticated Routes
    sealed class AuthScreens(val route: String) : NavigationRoutes() {
        data object NavigationRoute : AuthScreens(route = "Unauthenticated")
        data object Login : AuthScreens(route = "Login")
        data object Register : AuthScreens(route = "Register")
        data object ForgotPassword : AuthScreens(route = "ForgotPassword")
    }

    // Authenticated Routes
    sealed class AppScreens(
        val route: String
    ) : NavigationRoutes() {
        data object NavigationRoute : AppScreens(route = "AppScreens")
        data object Category : AppScreens(route = "Category")
        data object AIChat : AppScreens(route = "AIChat")
        data object Newsfeed : AppScreens(route = "Newsfeed")
        data object UserProfile : AppScreens(route = "UserProfile")
        data object Search : AppScreens(route = "Search")
        data object CreatePost : AppScreens(route = "CreatePost")
    }
}

fun shouldShowTopBar(currentRoute: String): Boolean {
    return when (currentRoute) {
        NavigationRoutes.AuthScreens.NavigationRoute.route -> false
        NavigationRoutes.AuthScreens.Login.route -> false
        NavigationRoutes.AuthScreens.ForgotPassword.route -> false
        NavigationRoutes.AuthScreens.Register.route -> false
        else -> true
    }
}

fun shouldShowBottomBar(currentRoute: String): Boolean {
    return when (currentRoute) {
        AppScreens.Category.route -> true
        AppScreens.AIChat.route -> true
        AppScreens.Newsfeed.route -> true
        AppScreens.UserProfile.route -> true
        else -> false
    }
}