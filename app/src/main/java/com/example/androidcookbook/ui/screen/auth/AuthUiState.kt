package com.example.androidcookbook.ui.screen.auth

data class AuthUiState(
    val openDialog: Boolean = false,
    val dialogMessage: String = "",
    val signInSuccess: Boolean = false,
    val signedIn: Boolean = false
)
