package com.example.androidcookbook.data.repositories

import com.example.androidcookbook.data.network.CategoriesService
import com.example.androidcookbook.model.Category
import javax.inject.Inject

class CategoriesRepository @Inject constructor(
    private val categoriesService: CategoriesService
) {
    suspend fun getCategories(): List<Category> = categoriesService.getCategories().categories
}

