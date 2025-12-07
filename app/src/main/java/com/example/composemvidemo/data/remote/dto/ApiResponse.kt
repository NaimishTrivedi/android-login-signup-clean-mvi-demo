package com.example.composemvidemo.data.remote.dto

data class ApiResponse<T>(
    val success: Boolean,
    val data: T? = null,
    val message: String? = null
)
