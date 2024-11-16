package com.example.androidcookbook.data

import com.example.androidcookbook.model.auth.RegisterRequest
import com.example.androidcookbook.model.auth.RegisterResponse
import com.example.androidcookbook.model.auth.SignInRequest
import com.example.androidcookbook.model.auth.SignInResponse
import com.example.androidcookbook.network.AuthService

class AuthRepository(
    private val authService: AuthService
) : Repository {
    suspend fun login(signInRequest: SignInRequest) = authService.signIn(signInRequest)
    suspend fun register(registerRequest: RegisterRequest) = authService.register(registerRequest)
}