package com.example.composemvidemo.domain.usecase

import com.example.composemvidemo.data.remote.dto.SignupRequest
import com.example.composemvidemo.data.remote.dto.UserData
import com.example.composemvidemo.domain.model.Resource
import com.example.composemvidemo.domain.repository.SignupRepository
import javax.inject.Inject

class SignupUseCase @Inject constructor(
    private val repository: SignupRepository
) {
    suspend operator fun invoke(signupRequest: SignupRequest): Resource<UserData?> {
        return try {
            val apiRes = repository.signup(signupRequest)
            if(apiRes.success) {
                Resource.Success(apiRes.data)
            }else{
                Resource.Error(apiRes.message ?: "Unknown error")
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Unknown error")
        }
    }
}