package com.example.androidcookbook.ui.nav

/**
 * Sealed class storing all navigation routes
 */
sealed class NavigationRoutes(
    val route: String,
    val hasTopBar: Boolean = true,
    val hasBottomBar: Boolean = false
) {

    // Unauthenticated Routes
    sealed class AuthScreens(
        route: String,
        hasTopBar: Boolean = false,
        hasBottomBar: Boolean = false
    ) : NavigationRoutes(route, hasTopBar, hasBottomBar) {
        data object NavigationRoute : AuthScreens(route = "Unauthenticated")
        data object Login : AuthScreens(route = "Login")
        data object Register : AuthScreens(route = "Register")
        data object ForgotPassword : AuthScreens(route = "ForgotPassword")
    }

    // Authenticated Routes
    sealed class AppScreens(
        route: String,
        hasTopBar: Boolean = true,
        hasBottomBar: Boolean = true,
    ) : NavigationRoutes(route, hasTopBar, hasBottomBar) {
        data object NavigationRoute : AppScreens(route = "AppScreens", hasBottomBar = false)
        data object Category : AppScreens(route = "Category")
        data object AIChat : AppScreens(route = "AIChat")
        data object Newsfeed : AppScreens(route = "Newsfeed")
        data object UserProfile : AppScreens(route = "UserProfile")
        data object Search : AppScreens(route = "Search", hasBottomBar = false)
        data object CreatePost : AppScreens(route = "CreatePost", hasBottomBar = false)
    }
}