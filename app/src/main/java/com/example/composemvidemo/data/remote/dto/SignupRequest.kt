package com.example.composemvidemo.data.remote.dto

data class SignupRequest(
    val name: String,
    val email: String,
    val mobile: String,
    val password: String
)
