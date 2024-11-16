package com.example.androidcookbook.ui.nav

/**
 * Sealed class storing all navigation routes
 */
sealed class NavigationRoutes {

    // Unauthenticated Routes
    sealed class AuthScreens(val route: String) : NavigationRoutes() {
        data object NavigationRoute : AuthScreens(route = "Unauthenticated")
        data object Login : AuthScreens(route = "Login")
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