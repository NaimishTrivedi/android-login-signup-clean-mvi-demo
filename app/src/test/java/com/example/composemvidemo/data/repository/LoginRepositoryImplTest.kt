package com.example.composemvidemo.data.repository

import com.example.composemvidemo.data.remote.api.AuthApi
import com.example.composemvidemo.data.remote.dto.ApiResponse
import com.example.composemvidemo.data.remote.dto.LoginRequest
import com.example.composemvidemo.data.remote.dto.UserData
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import java.io.IOException
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@OptIn(ExperimentalCoroutinesApi::class)
class LoginRepositoryImplTest {
    private val api: AuthApi = mockk()
    private lateinit var repository: LoginRepositoryImpl

    @Before
    fun setUp() {
        repository = LoginRepositoryImpl(api)
    }

    @Test
    fun `login success returns LoginResponse`() = runTest {
        val expected = ApiResponse(
            success = true,
            data = UserData(
                id = "1",
                name = "Naimish Trivedi",
                email = "naimishtrivedi7@gmail.com",
                mobile = "0123456789"
            ),
            message = "Login Success!"
        )
        coEvery { api.login(LoginRequest("naimishtrivedi7@gmail.com", "12345678")) } returns expected

        val result = repository.login("naimishtrivedi7@gmail.com", "12345678")

        assertEquals(expected, result)
        coVerify(exactly = 1) { api.login(any()) }
    }

    @Test
    fun `login failure throws exception`() = runTest {
        coEvery { api.login(any()) } throws IOException("network fail")

        // repository.login should throw (safeApiCall rethrows)
        val thrown = assertFailsWith<IOException> {
            repository.login("a@b.com", "123456789")
        }

        assertEquals("network fail", thrown.message)
        coVerify(exactly = 1) { api.login(any()) }
    }

}