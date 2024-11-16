package com.example.androidcookbook.network

import com.example.androidcookbook.model.api.ApiResponse
import com.example.androidcookbook.model.auth.SignInRequest
import com.example.androidcookbook.model.auth.RegisterRequest
import com.example.androidcookbook.model.auth.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): Call<RegisterResponse>

    @POST("auth/login")
    suspend fun signIn(@Body request: SignInRequest): Call<ApiResponse>
}

