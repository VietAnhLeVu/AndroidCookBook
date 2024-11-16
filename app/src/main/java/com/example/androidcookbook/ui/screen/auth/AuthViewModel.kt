package com.example.androidcookbook.ui.screen.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.androidcookbook.CookbookApplication
import com.example.androidcookbook.data.AuthRepository
import com.example.androidcookbook.model.api.ApiResponse
import com.example.androidcookbook.model.auth.RegisterRequest
import com.example.androidcookbook.model.auth.RegisterResponse
import com.example.androidcookbook.model.auth.SignInRequest
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthViewModel(private val authRepository: AuthRepository) : ViewModel() {
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
        viewModelScope.launch(Dispatchers.IO) {
            authRepository.register(req).enqueue(object : Callback<RegisterResponse> {
                override fun onResponse(
                    call: Call<RegisterResponse>,
                    response: Response<RegisterResponse>,
                ) {
                    ChangeOpenDialog(true)
                    if (response.isSuccessful) {
                        // Trường hợp đăng ký thành công
                        val registerResponse = response.body()
                        Log.d("Register", "Success: ${registerResponse?.message}")
                        ChangeDialogMessage(registerResponse?.message.toString())
                    } else {
                        // Trường hợp lỗi (400, 500, etc.)
                        val errorBody = response.errorBody()?.string()
                        try {
                            // Chuyển đổi errorBody thành `RegisterResponse`
                            val gson = Gson()
                            val errorResponse =
                                gson.fromJson(errorBody, RegisterResponse::class.java)
                            val message = when (val msg = errorResponse.message) {
                                is String -> msg
                                is List<*> -> msg.joinToString(", ")
                                else -> "Unknown message format"
                            }
                            Log.e("Register", "Error: $message")
                            ChangeDialogMessage(message)
                        } catch (e: Exception) {
                            Log.e("Register", "Unknown Error: ${e.message}")
                            ChangeDialogMessage(e.message.toString())
                        }
                    }
                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    Log.e("Register", "Failure: ${t.message}")
                }
            })
        }
    }

    fun SignIn(req: SignInRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            authRepository.login(req).enqueue(object : Callback<ApiResponse> {
                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                    ChangeOpenDialog(true)
                    if (response.isSuccessful) {
                        // Trường hợp đăng ký thành công
                        val registerResponse = response.body()
                        Log.d("Register", "Success: ${registerResponse?.message}")
                        SignInSuccess()
                        ChangeDialogMessage(registerResponse?.message.toString())
                    } else {
                        // Trường hợp lỗi (400, 500, etc.)
                        val errorBody = response.errorBody()?.string()
                        try {
                            // Chuyển đổi errorBody thành `RegisterResponse`
                            val gson = Gson()
                            val errorResponse = gson.fromJson(errorBody, ApiResponse::class.java)
                            val message = when (val msg = errorResponse.message) {
                                is String -> msg
                                is List<*> -> msg.joinToString(", ")
                                else -> "Unknown message format"
                            }
                            Log.e("Register", "Error: $message")
                            ChangeDialogMessage(message)
                        } catch (e: Exception) {
                            Log.e("Register", "Unknown Error: ${e.message}")
                            ChangeDialogMessage(e.message.toString())
                        }
                    }
                }

                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    Log.e("Register", "Failure: ${t.message}")
                }
            })
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as CookbookApplication)
                val authRepository = application.authContainer.repository as AuthRepository
                AuthViewModel(authRepository = authRepository)
            }
        }
    }
}