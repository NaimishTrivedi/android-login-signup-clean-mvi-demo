package com.example.composemvidemo.domain.usecase

import com.example.composemvidemo.domain.repository.SessionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MVIDemoUseCase @Inject constructor(
    private val sessionRepository: SessionRepository
) {
    fun isLoggedIn(): Flow<Boolean> =
        sessionRepository.isLoggedIn()
}