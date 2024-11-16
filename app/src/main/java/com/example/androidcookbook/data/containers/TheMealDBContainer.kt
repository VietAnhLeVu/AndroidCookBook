package com.example.androidcookbook.data.containers

import com.example.androidcookbook.data.repositories.CookbookRepository
import com.example.androidcookbook.data.network.CookbookApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val THE_MEAL_DB = "https://www.themealdb.com/api/json/v1/1/"

class TheMealDBContainer : AppContainer {
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(THE_MEAL_DB)
        .build()

    private val retrofitService: CookbookApiService by lazy {
        retrofit.create(CookbookApiService::class.java)
    }

    override val repository: CookbookRepository by lazy {
        CookbookRepository(retrofitService)
    }
}