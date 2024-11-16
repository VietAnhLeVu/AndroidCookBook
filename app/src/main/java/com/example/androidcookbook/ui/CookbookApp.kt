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
import com.example.androidcookbook.ui.nav.appScreens
import com.example.androidcookbook.ui.nav.authScreens
import com.example.androidcookbook.ui.screen.CookbookScreens
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

    val currentScreen = CookbookScreens.valueOf(
        backStackEntry?.destination?.route ?: CookbookScreens.Category.name
    )

    val viewModel: CookbookViewModel = viewModel()
    val categoryViewModel: CategoryViewModel = viewModel(factory = CategoryViewModel.Factory)
    val uiState by viewModel.uiState.collectAsState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            if (!currentScreen.hasTopBar) {
                return@Scaffold
            }
            if (currentScreen == CookbookScreens.Search) {
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
                        navController.navigate(CookbookScreens.Search.name)
                    },
                    onCreatePostClick = {
                        navController.navigate(CookbookScreens.CreatePost.name)
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
            if (!currentScreen.hasBottomBar) {
                return@Scaffold
            }
            CookbookBottomNavigationBar(
                onHomeClick = {
                    if (currentScreen != CookbookScreens.Category) {
                        navController.navigate(route = CookbookScreens.Category.name)
                    }
                },
                onChatClick = {
                    if (currentScreen != CookbookScreens.AIChat) {
                        navController.navigate(route = CookbookScreens.AIChat.name)
                    }
                },
                onNewsfeedClick = {
                    if (currentScreen != CookbookScreens.Newsfeed) {
                        navController.navigate(route = CookbookScreens.Newsfeed.name)
                    }
                },
                onUserProfileClick = {
                    if (currentScreen != CookbookScreens.UserProfile) {
                        navController.navigate(route = CookbookScreens.UserProfile.name)
                    }
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = CookbookScreens.Category.name,
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