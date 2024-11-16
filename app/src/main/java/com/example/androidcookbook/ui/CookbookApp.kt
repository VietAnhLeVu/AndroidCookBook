package com.example.androidcookbook.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.androidcookbook.ui.component.appbars.CookbookAppBar
import com.example.androidcookbook.ui.component.appbars.CookbookBottomNavigationBar
import com.example.androidcookbook.ui.component.appbars.SearchBar
import com.example.androidcookbook.ui.nav.NavigationRoutes
import com.example.androidcookbook.ui.nav.appScreens
import com.example.androidcookbook.ui.nav.authScreens
import com.example.androidcookbook.ui.nav.shouldShowBottomBar
import com.example.androidcookbook.ui.nav.shouldShowTopBar
import com.example.androidcookbook.ui.viewmodel.CategoryViewModel
import com.example.androidcookbook.ui.viewmodel.CookbookViewModel
import com.example.androidcookbook.ui.viewmodel.SignViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CookbookApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = (
        backStackEntry?.destination?.route ?: NavigationRoutes.AuthScreens.Login.route
    )

    val viewModel: CookbookViewModel = viewModel()
    val categoryViewModel: CategoryViewModel = viewModel(factory = CategoryViewModel.Factory)
    val uiState by viewModel.uiState.collectAsState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            if (!shouldShowTopBar(currentScreen)) {
                return@Scaffold
            }
            if (currentScreen == NavigationRoutes.AppScreens.Search.route) {
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
                        navController.navigate(NavigationRoutes.AppScreens.CreatePost.route)
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
            if (!shouldShowBottomBar(currentScreen)) {
                return@Scaffold
            }
            CookbookBottomNavigationBar(
                onHomeClick = {
                    if (currentScreen != NavigationRoutes.AppScreens.Category.route) {
                        navController.navigate(route = NavigationRoutes.AppScreens.Category.route)
                    }
                },
                onChatClick = {
                    if (currentScreen != NavigationRoutes.AppScreens.AIChat.route) {
                        navController.navigate(route = NavigationRoutes.AppScreens.AIChat.route)
                    }
                },
                onNewsfeedClick = {
                    if (currentScreen != NavigationRoutes.AppScreens.Newsfeed.route) {
                        navController.navigate(route = NavigationRoutes.AppScreens.Newsfeed.route)
                    }
                },
                onUserProfileClick = {
                    if (currentScreen != NavigationRoutes.AppScreens.UserProfile.route) {
                        navController.navigate(route = NavigationRoutes.AppScreens.UserProfile.route)
                    }
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
            authScreens(navController = navController, viewModel = SignViewModel())
            appScreens(navController = navController, viewModel = viewModel, categoryViewModel = categoryViewModel, uiState = uiState)
        }
    }
}

@Preview
@Composable
fun CookbookAppPreview() {
    CookbookApp()
}