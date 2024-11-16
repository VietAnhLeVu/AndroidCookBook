package com.example.androidcookbook.model.auth

data class SignInRequest(
    val username: String,
    val password: String
)

data class SignInResponse(
    val statusCode: Int,
    val timestamp: String,
    val path: String,
    val message: String,
    val error: String
)
