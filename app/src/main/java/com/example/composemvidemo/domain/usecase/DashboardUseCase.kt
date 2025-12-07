package com.example.composemvidemo.domain.usecase

import com.example.composemvidemo.domain.repository.SessionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DashboardUseCase @Inject constructor(
    private val sessionRepository: SessionRepository
) {
    // 1. Get username as Flow<String>
    fun getUserName(): Flow<String> = sessionRepository.getUserName()

    // 2. Logout (clear session)
    suspend fun logout() = sessionRepository.clearSession()
}