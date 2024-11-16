package com.example.androidcookbook.ui.screen.category

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.androidcookbook.CookbookApplication
import com.example.androidcookbook.data.CookbookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class CategoryViewModel(private val cookbookRepository: CookbookRepository) : ViewModel() {

    var categoryUiState: CategoryUiState by mutableStateOf(CategoryUiState.Loading)
        private set

    init {
        getCategories()
    }

    fun getCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            categoryUiState = CategoryUiState.Loading
            categoryUiState = try {
                CategoryUiState.Success(cookbookRepository.getCategories())
            } catch (e: IOException) {
                CategoryUiState.Error
            } catch (e: HttpException) {
                CategoryUiState.Error
            }
        }
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as CookbookApplication)
                val cookbookRepository = application.theMealDBContainer.repository as CookbookRepository
                CategoryViewModel(cookbookRepository = cookbookRepository)
            }
        }
    }
}