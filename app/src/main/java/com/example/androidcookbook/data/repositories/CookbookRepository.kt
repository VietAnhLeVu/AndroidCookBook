package com.example.androidcookbook.data.repositories

import com.example.androidcookbook.model.Category
import com.example.androidcookbook.data.network.CookbookApiService

class CookbookRepository(
    private val cookbookApiService: CookbookApiService
) : Repository {
    suspend fun getCategories(): List<Category> = cookbookApiService.getCategories().categories
}

