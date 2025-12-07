package com.example.composemvidemo.data.repository

import com.example.composemvidemo.data.BaseRepository
import com.example.composemvidemo.data.remote.api.AuthApi
import com.example.composemvidemo.data.remote.dto.ApiResponse
import com.example.composemvidemo.data.remote.dto.SignupRequest
import com.example.composemvidemo.data.remote.dto.UserData
import com.example.composemvidemo.domain.repository.SignupRepository
import javax.inject.Inject

class SignupRepositoryImpl @Inject constructor(
    private val api: AuthApi
) : SignupRepository, BaseRepository() {

    override suspend fun signup(signupReq: SignupRequest): ApiResponse<UserData> {
        return safeApiCall {
            api.signup(signupReq)
        }
    }
}