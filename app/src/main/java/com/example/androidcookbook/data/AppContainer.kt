package com.example.androidcookbook.data


import com.example.androidcookbook.network.AuthService
import com.example.androidcookbook.network.CookbookApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val THE_MEAL_DB = "https://www.themealdb.com/api/json/v1/1/"
private const val COOKBOOK_BE = "https://cookbookbe.onrender.com/"

interface AppContainer {
    val repository: Repository
}

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

class AuthContainer : AppContainer {
    private val serviceBuilder: Retrofit = Retrofit.Builder()
        .baseUrl(COOKBOOK_BE)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val authService: AuthService by lazy {
        serviceBuilder.create(AuthService::class.java)
    }

    override val repository: AuthRepository by lazy {
        AuthRepository(authService)
    }
}