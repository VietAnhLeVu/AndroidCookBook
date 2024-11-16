package com.example.androidcookbook.ui

data class CookbookUiState (
    val canNavigateBack: Boolean = false,
    val searchQuery: String = "",
)