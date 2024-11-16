package com.example.androidcookbook.data.containers

import com.example.androidcookbook.data.repositories.AuthRepository
import com.example.androidcookbook.data.network.AuthService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val COOKBOOK_BE = "https://cookbookbe.onrender.com/"

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