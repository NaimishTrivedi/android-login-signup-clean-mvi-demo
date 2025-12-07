package com.example.composemvidemo.domain.repository

import com.example.composemvidemo.data.remote.dto.ApiResponse
import com.example.composemvidemo.data.remote.dto.SignupRequest
import com.example.composemvidemo.data.remote.dto.UserData

interface SignupRepository {
    suspend fun signup(signupReq: SignupRequest): ApiResponse<UserData>
}