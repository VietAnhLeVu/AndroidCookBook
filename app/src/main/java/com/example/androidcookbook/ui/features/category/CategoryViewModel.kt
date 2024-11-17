package com.example.androidcookbook.ui.features.category

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidcookbook.data.repositories.CategoriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val categoriesRepository: CategoriesRepository
) : ViewModel() {

    var categoryUiState: CategoryUiState by mutableStateOf(CategoryUiState.Loading)
        private set

    init {
        getCategories()
    }

    fun getCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            categoryUiState = CategoryUiState.Loading
            categoryUiState = try {
                CategoryUiState.Success(categoriesRepository.getCategories())
            } catch (e: IOException) {
                CategoryUiState.Error
            } catch (e: HttpException) {
                CategoryUiState.Error
            }
        }
    }
}