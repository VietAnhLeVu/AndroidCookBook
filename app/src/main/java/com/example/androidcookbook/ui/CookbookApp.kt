package com.example.androidcookbook.ui

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.androidcookbook.ui.components.appbars.CookbookAppBar
import com.example.androidcookbook.ui.components.appbars.CookbookBottomNavigationBar
import com.example.androidcookbook.ui.components.appbars.SearchBar
import com.example.androidcookbook.ui.nav.NavigationRoutes
import com.example.androidcookbook.ui.nav.graphs.appScreens
import com.example.androidcookbook.ui.nav.graphs.authScreens
import com.example.androidcookbook.ui.nav.utils.navigateIfNotOn
import com.example.androidcookbook.ui.features.category.CategoryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CookbookApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    Log.d("BACK_STACK_ENTRY", backStackEntry.toString())

    val currentRoute = backStackEntry?.destination?.route
    // Memoize the result to avoid recomputing on each recomposition
    val currentScreen = remember(currentRoute) {
        NavigationRoutes.AppScreens::class.nestedClasses
            .mapNotNull { it.objectInstance as? NavigationRoutes }
            .find { it.route == currentRoute }
    }

    val showTopBar = currentScreen?.hasTopBar ?: false
    val showBottomBar = currentScreen?.hasBottomBar ?: false

    val viewModel: CookbookViewModel = viewModel()
    val categoryViewModel: CategoryViewModel = hiltViewModel<CategoryViewModel>()

    val uiState by viewModel.uiState.collectAsState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            if (!showTopBar) {
                return@Scaffold
            }
            if (currentScreen is NavigationRoutes.AppScreens.Search) {
                SearchBar(
                    onValueChange = { updatedSearchQuery ->
                        viewModel.updateSearchQuery(updatedSearchQuery)
                    },
                    navigateBackAction = { navController.navigateUp() },
                    searchQuery = uiState.searchQuery
                )
            } else {
                CookbookAppBar(
                    showBackButton = uiState.canNavigateBack,
                    searchButtonAction = {
                        navController.navigate(NavigationRoutes.AppScreens.Search.route)
                    },
                    onCreatePostClick = {
                        navController.navigateIfNotOn(NavigationRoutes.AppScreens.CreatePost.route)
                        viewModel.updateCanNavigateBack(true)
                    },
                    onMenuButtonClick = {
                        //TODO: Add menu button
                    },
                    onBackButtonClick = {
                        navController.navigateUp()
                        viewModel.updateCanNavigateBack(false)
                    },
                    scrollBehavior = scrollBehavior
                )
            }
        },
        bottomBar = {
            if (!showBottomBar) {
                return@Scaffold
            }
            CookbookBottomNavigationBar(
                onHomeClick = {
                    navController.navigateIfNotOn(NavigationRoutes.AppScreens.Category.route)
                },
                onChatClick = {
                    navController.navigateIfNotOn(NavigationRoutes.AppScreens.AIChat.route)
                },
                onNewsfeedClick = {
                    navController.navigateIfNotOn(NavigationRoutes.AppScreens.Newsfeed.route)
                },
                onUserProfileClick = {
                    navController.navigateIfNotOn(NavigationRoutes.AppScreens.UserProfile.route)
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = NavigationRoutes.AuthScreens.NavigationRoute.route,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            authScreens(navController = navController)
            appScreens(navController = navController, viewModel = viewModel, categoryViewModel = categoryViewModel, uiState = uiState)
        }
    }
}

@Preview
@Composable
fun CookbookAppPreview() {
    CookbookApp()
}