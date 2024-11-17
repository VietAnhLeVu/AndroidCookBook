package com.example.androidcookbook.data.network

import com.example.androidcookbook.domain.model.auth.RegisterRequest
import com.example.androidcookbook.domain.model.auth.RegisterResponse
import com.example.androidcookbook.domain.model.auth.SignInRequest
import com.example.androidcookbook.domain.model.auth.SignInResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>

    @POST("auth/login")
    suspend fun signIn(@Body request: SignInRequest): Response<SignInResponse>
}

