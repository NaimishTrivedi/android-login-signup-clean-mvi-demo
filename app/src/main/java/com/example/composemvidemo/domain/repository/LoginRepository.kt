package com.example.composemvidemo.domain.repository

import com.example.composemvidemo.data.remote.dto.ApiResponse
import com.example.composemvidemo.data.remote.dto.UserData

interface LoginRepository {
    suspend fun login(email: String, password: String): ApiResponse<UserData>

}