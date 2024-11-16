package com.example.androidcookbook.ui.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.androidcookbook.ui.screen.aichat.AIChatScreen
import com.example.androidcookbook.ui.screen.category.CategoryScreen
import com.example.androidcookbook.ui.screen.common.CreatePostScreen
import com.example.androidcookbook.ui.screen.newsfeed.NewsfeedScreen
import com.example.androidcookbook.ui.screen.common.SearchScreen
import com.example.androidcookbook.ui.screen.userprofile.UserProfileScreen
import com.example.androidcookbook.ui.uistate.CookbookUiState
import com.example.androidcookbook.ui.viewmodel.CategoryViewModel
import com.example.androidcookbook.ui.viewmodel.CookbookViewModel

/**
 * Authenticated screens nav graph builder
 */
fun NavGraphBuilder.appScreens(
    categoryViewModel: CategoryViewModel,
    uiState: CookbookUiState,
    navController: NavHostController,
    viewModel: CookbookViewModel,
) {
    navigation(
        route = NavigationRoutes.AppScreens.NavigationRoute.route,
        startDestination = NavigationRoutes.AppScreens.Category.route,
    ) {
        composable(route = NavigationRoutes.AppScreens.Category.route) {
            CategoryScreen(categoryUiState = categoryViewModel.categoryUiState)
        }
        composable(route = NavigationRoutes.AppScreens.Search.route) {
            SearchScreen(
                result = uiState.searchQuery,
                onBackButtonClick = {
                    navController.navigateUp()
                    viewModel.updateCanNavigateBack(false)
                }
            )
        }
        composable(route = NavigationRoutes.AppScreens.AIChat.route) {
            AIChatScreen()
        }
        composable(route = NavigationRoutes.AppScreens.Newsfeed.route) {
            NewsfeedScreen()
        }
        composable(route = NavigationRoutes.AppScreens.UserProfile.route) {
            UserProfileScreen()
        }
        composable(route = NavigationRoutes.AppScreens.CreatePost.route) {
            CreatePostScreen(
                onPostButtonClick = {
                    //TODO: Connect to database
                },
                onBackButtonClick = {
                    navController.navigateUp()
                    viewModel.updateCanNavigateBack(false)
                },
            )
        }
    }
}