package com.example.composemvidemo.data.repository

import com.example.composemvidemo.data.BaseRepository
import com.example.composemvidemo.data.remote.api.AuthApi
import com.example.composemvidemo.data.remote.dto.ApiResponse
import com.example.composemvidemo.data.remote.dto.LoginRequest
import com.example.composemvidemo.data.remote.dto.UserData
import com.example.composemvidemo.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val api: AuthApi
) : LoginRepository, BaseRepository() {

    override suspend fun login(email: String, password: String): ApiResponse<UserData> {
        return safeApiCall {
            api.login(LoginRequest(email, password))
        }
    }
}