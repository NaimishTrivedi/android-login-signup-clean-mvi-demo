package com.example.composemvidemo.domain.repository

import kotlinx.coroutines.flow.Flow

interface SessionRepository {
    suspend fun saveLoginData(userId: String, name: String, email: String)
    fun getUserName(): Flow<String>
    suspend fun clearSession()
    fun isLoggedIn(): Flow<Boolean>
}