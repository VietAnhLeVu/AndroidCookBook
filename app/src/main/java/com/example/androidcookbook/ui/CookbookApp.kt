package com.example.androidcookbook.ui

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.androidcookbook.ui.component.CookbookAppBar
import com.example.androidcookbook.ui.component.CookbookBottomNavigationBar
import com.example.androidcookbook.ui.component.SearchBar
import com.example.androidcookbook.ui.screen.AIChatScreen
import com.example.androidcookbook.ui.screen.CategoryScreen
import com.example.androidcookbook.ui.screen.NewsfeedScreen
import com.example.androidcookbook.ui.screen.SearchScreen
import com.example.androidcookbook.ui.screen.UserProfileScreen
import com.example.androidcookbook.ui.utils.CookbookScreen
import com.example.androidcookbook.ui.viewmodel.CategoryViewModel
import com.example.androidcookbook.ui.viewmodel.CookbookViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CookbookApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = CookbookScreen.valueOf(
        backStackEntry?.destination?.route ?: CookbookScreen.Category.name
    )

    val viewModel: CookbookViewModel = viewModel()
    val categoryViewModel: CategoryViewModel = viewModel(factory = CategoryViewModel.Factory)
    val uiState by viewModel.uiState.collectAsState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            if (currentScreen != CookbookScreen.Search) {
                CookbookAppBar(showBackButton = uiState.canNavigateBack, searchButtonAction = {
                    navController.navigate(CookbookScreen.Search.name)
                }, scrollBehavior = scrollBehavior)
            } else {
                SearchBar(
                    onValueChange =
                    { updatedSearchQuery -> viewModel.updateSearchQuery(updatedSearchQuery) },
                    navigateBackAction = { navController.navigateUp() },
                    searchQuery = uiState.searchQuery
                )
            }
        },
        bottomBar = {
            CookbookBottomNavigationBar(
                onHomeClick = { navController.navigate(route = CookbookScreen.Category.name) },
                onChatClick = { navController.navigate(route = CookbookScreen.AIChat.name) },
                onNewsfeedClick = { navController.navigate(route = CookbookScreen.Newsfeed.name) },
                onUserProfileClick = { navController.navigate(route = CookbookScreen.UserProfile.name) }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = CookbookScreen.Category.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
//            enterTransition = { EnterTransition.None },
//            exitTransition = { ExitTransition.None }
        ) {
            composable(route = CookbookScreen.Category.name) {
                CategoryScreen(categoryUiState = categoryViewModel.categoryUiState)
            }
            composable(route = CookbookScreen.Search.name)
            {
                SearchScreen(uiState.searchQuery)
            }
            composable(route = CookbookScreen.AIChat.name) {
                AIChatScreen()
            }
            composable(route = CookbookScreen.Newsfeed.name) {
                NewsfeedScreen()
            }
            composable(route = CookbookScreen.UserProfile.name) {
                UserProfileScreen()
            }
        }
    }
}

@Preview
@Composable
fun CookbookAppPreview() {
    CookbookApp()
}