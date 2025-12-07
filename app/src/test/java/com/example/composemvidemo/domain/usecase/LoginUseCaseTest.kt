package com.example.composemvidemo.domain.usecase

import com.example.composemvidemo.data.remote.dto.ApiResponse
import com.example.composemvidemo.data.remote.dto.UserData
import com.example.composemvidemo.domain.model.Resource
import com.example.composemvidemo.domain.repository.LoginRepository
import com.example.composemvidemo.domain.repository.SessionRepository
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class LoginUseCaseTest {
    // Simple fake success repository
    class FakeSuccessRepo : LoginRepository {
        override suspend fun login(email: String, password: String) =
            ApiResponse(
                success = true,
                data = UserData(
                    id = "1",
                    name = "Naimish Trivedi",
                    email = "naimishtrivedi7@gmail.com",
                    mobile = "0123456789"
                ),
                message = "Login Success!"
            )
    }

    class FakeErrorRepo : LoginRepository {
        override suspend fun login(email: String, password: String): ApiResponse<UserData> {
            throw IllegalStateException("Network Error")
        }
    }

    class FakeSessionRepo : SessionRepository {
        override suspend fun saveLoginData(
            userId: String,
            name: String,
            email: String
        ) {

        }

        override fun getUserName(): Flow<String> {
            return flow {

            }
        }

        override suspend fun clearSession() {

        }

        override fun isLoggedIn(): Flow<Boolean> {
            return flow {

            }
        }

    }

    @Test
    fun `usecase returns Resource_Success when repository returns data`() = runTest {
        val useCase = LoginUseCase(FakeSuccessRepo(), FakeSessionRepo())

        val res = useCase("naimishtrivedi7@gmail.com", "12345678")

        assertTrue(res is Resource.Success)
        assertEquals("1", (res as Resource.Success).data?.id)
    }

    @Test
    fun `usecase returns Resource_Error when repository throws`() = runTest {
        val useCase = LoginUseCase(FakeErrorRepo(),FakeSessionRepo())

        val res = useCase("naimishtrivedi7@gmail.com", "12345678")

        assertTrue(res is Resource.Error)
        assertEquals("Network Error", (res as Resource.Error).message)
    }
}