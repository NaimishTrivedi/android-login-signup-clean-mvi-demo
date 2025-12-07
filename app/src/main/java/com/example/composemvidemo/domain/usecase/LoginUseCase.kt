package com.example.composemvidemo.domain.usecase

import com.example.composemvidemo.data.remote.dto.UserData
import com.example.composemvidemo.domain.model.Resource
import com.example.composemvidemo.domain.repository.LoginRepository
import com.example.composemvidemo.domain.repository.SessionRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: LoginRepository,
    private val sessionRepository: SessionRepository
) {
    suspend operator fun invoke(email: String, password: String): Resource<UserData?> {
        return try {
            val apiRes = repository.login(email, password)
            if (apiRes.success) {
                apiRes.data?.let {
                    sessionRepository.saveLoginData(
                        userId = apiRes.data.id,
                        email = apiRes.data.email,
                        name = apiRes.data.name
                    )
                }

                Resource.Success(apiRes.data)
            } else {
                Resource.Error(apiRes.message ?: "Unknown error")
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Unknown error")
        }
    }
}