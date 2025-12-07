package com.example.composemvidemo.data.remote.api

import com.example.composemvidemo.data.remote.dto.ApiResponse
import com.example.composemvidemo.data.remote.dto.LoginRequest
import com.example.composemvidemo.data.remote.dto.SignupRequest
import com.example.composemvidemo.data.remote.dto.UserData
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("login")
    suspend fun login(@Body request: LoginRequest): ApiResponse<UserData>

    @POST("signup")
    suspend fun signup(@Body request: SignupRequest): ApiResponse<UserData>
}