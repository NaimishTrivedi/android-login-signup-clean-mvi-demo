package com.example.composemvidemo.ui.features.login

import com.example.composemvidemo.data.remote.dto.UserData

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorEmail: Boolean = false,
    val errorPassword: Boolean = false,
    val loginResponse: UserData? = null
)