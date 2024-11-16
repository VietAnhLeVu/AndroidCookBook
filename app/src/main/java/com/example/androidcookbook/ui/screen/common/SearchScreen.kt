package com.example.androidcookbook.ui.screen.common

import androidx.activity.compose.BackHandler
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SearchScreen(
    result: String,
    onBackButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    BackHandler { onBackButtonClick() }
    Text(text = result)
}

