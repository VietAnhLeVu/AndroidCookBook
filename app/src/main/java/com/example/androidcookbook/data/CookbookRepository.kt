package com.example.androidcookbook.data

import com.example.androidcookbook.model.Category
import com.example.androidcookbook.network.CookbookApiService

class CookbookRepository(
    private val cookbookApiService: CookbookApiService
) : Repository {
    suspend fun getCategories(): List<Category> = cookbookApiService.getCategories().categories
}

