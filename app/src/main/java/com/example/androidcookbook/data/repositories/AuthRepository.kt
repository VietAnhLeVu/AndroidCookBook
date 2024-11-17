package com.example.androidcookbook.data.repositories

import com.example.androidcookbook.data.network.AuthService
import com.example.androidcookbook.domain.model.auth.RegisterRequest
import com.example.androidcookbook.domain.model.auth.SignInRequest
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authService: AuthService
) {
    suspend fun login(signInRequest: SignInRequest) = authService.signIn(signInRequest)
    suspend fun register(registerRequest: RegisterRequest) = authService.register(registerRequest)
}