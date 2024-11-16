package com.example.androidcookbook.model.auth

data class RegisterRequest(
    val username: String,
    val password: String,
    val email: String
)

data class RegisterResponse(
    val statusCode: Int,
    val timestamp: String,
    val path: String,
    val message: String,
    val error: String
)
