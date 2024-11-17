package com.example.androidcookbook.ui.features.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidcookbook.data.repositories.AuthRepository
import com.example.androidcookbook.model.auth.RegisterRequest
import com.example.androidcookbook.model.auth.SignInRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    fun ChangeOpenDialog(open: Boolean) {
        _uiState.update {
            it.copy(
                openDialog = open
            )
        }
    }

    fun ChangeDialogMessage(message: String) {
        _uiState.update {
            it.copy(
                dialogMessage = message
            )
        }
    }

    fun SignInSuccess() {
        _uiState.update {
            it.copy(
                signInSuccess = true
            )
        }
    }

    fun SignUp(req: RegisterRequest) {
        viewModelScope.launch {
            val response = authRepository.register(req)
            ChangeOpenDialog(true)
            if (response.isSuccessful) {
                // Trường hợp đăng ký thành công
                val registerResponse = response.body()
                Log.d("Register", "Success: ${registerResponse?.message}")
                registerResponse?.message?.let { ChangeDialogMessage(it) }
            } else {
                Log.e("Register", "Error: ${response}")
                ChangeDialogMessage(response.toString())
            }
        }
    }

    fun signIn(req: SignInRequest) {
        viewModelScope.launch {
            try {
                val response = authRepository.login(req)

                if (response.isSuccessful) {
                    val signInResponse = response.body()
                    Log.d("Login", "Success: $signInResponse}")
                    SignInSuccess()
                    signInResponse?.message?.let { ChangeDialogMessage(it) }
                } else if (response.code() == 404) {
                    Log.e("Login", "Request timed out.")
                    ChangeDialogMessage("Cannot establish connection")
                } else {
                    Log.e("Login", "Error: $response")
                    ChangeDialogMessage("Wrong username or password")
                }
            } catch (e: Exception) {
                when (e) {
                    is java.net.SocketTimeoutException -> {
                        Log.e("Login", "Socket Timeout: ${e}")
                        ChangeDialogMessage("Request timed out. Please try again.")
                    }
                    else -> {
                        Log.e("Login", "Unexpected error: ${e.message}")
                        ChangeDialogMessage("An unexpected error occurred. Please try again.")
                    }
                }
            }
            ChangeOpenDialog(true)
        }
    }
}