package com.example.androidcookbook.ui.nav.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.androidcookbook.ui.features.aichat.AIChatScreen
import com.example.androidcookbook.ui.features.category.CategoryScreen
import com.example.androidcookbook.ui.features.post.CreatePostScreen
import com.example.androidcookbook.ui.features.newsfeed.NewsfeedScreen
import com.example.androidcookbook.ui.features.search.SearchScreen
import com.example.androidcookbook.ui.features.userprofile.UserProfileScreen
import com.example.androidcookbook.ui.CookbookUiState
import com.example.androidcookbook.ui.features.category.CategoryViewModel
import com.example.androidcookbook.ui.CookbookViewModel
import com.example.androidcookbook.ui.nav.NavigationRoutes

/**
 * App screens nav graph builder
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