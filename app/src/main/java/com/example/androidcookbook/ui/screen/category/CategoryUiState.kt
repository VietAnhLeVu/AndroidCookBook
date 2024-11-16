package com.example.androidcookbook.ui.screen.category

import com.example.androidcookbook.model.Category

sealed interface CategoryUiState{
    data class Success(val categories: List<Category>) : CategoryUiState
    data object Error : CategoryUiState
    data object Loading : CategoryUiState
}
