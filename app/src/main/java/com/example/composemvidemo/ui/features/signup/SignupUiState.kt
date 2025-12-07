package com.example.composemvidemo.ui.features.signup

import com.example.composemvidemo.data.remote.dto.UserData

data class SignupUiState(
    val name: String = "",
    val email: String = "",
    val mobile: String = "",
    val password: String = "",
    val cpassword: String = "",
    val isLoading: Boolean = false,
    val errorName: Boolean = false,
    val errorEmail: Boolean = false,
    val errorMobile: Boolean = false,
    val errorPassword: Boolean = false,
    val errorCpassword: Boolean = false,
    val loginResponse: UserData? = null
)