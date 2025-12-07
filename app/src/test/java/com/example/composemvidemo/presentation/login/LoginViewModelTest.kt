package com.example.composemvidemo.presentation.login

import app.cash.turbine.test
import com.example.composemvidemo.data.remote.dto.UserData
import com.example.composemvidemo.domain.model.Resource
import com.example.composemvidemo.domain.usecase.LoginUseCase
import com.example.composemvidemo.events.BaseUIEvent
import com.example.composemvidemo.events.LoginEvent
import com.example.composemvidemo.ui.features.login.LoginViewModel
import com.example.composemvidemo.util.MainDispatcherRule
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {
    // LiveData instant execution
    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private lateinit var loginUseCase: LoginUseCase
    private lateinit var viewModel: LoginViewModel

    @Before
    fun setUp() {
        loginUseCase = mockk()
        viewModel = LoginViewModel(loginUseCase)
    }

    @Before
    fun setup() {
        loginUseCase = mockk()
        viewModel = LoginViewModel(loginUseCase)
    }

    // ----------------------------------------------------------
    // STATE TESTS
    // ----------------------------------------------------------

    @Test
    fun `email changed should update state`() = runTest {
        viewModel.onIntent(LoginViewModel.Intent.EmailChanged("abc@gmail.com"))
        assertThat(viewModel.state.value.email).isEqualTo("abc@gmail.com")
        assertThat(viewModel.state.value.errorEmail).isFalse()
    }

    @Test
    fun `password changed should update state`() = runTest {
        viewModel.onIntent(LoginViewModel.Intent.PasswordChanged("1234"))
        assertThat(viewModel.state.value.password).isEqualTo("1234")
        assertThat(viewModel.state.value.errorPassword).isFalse()
    }

    // ----------------------------------------------------------
    // VALIDATION TESTS
    // ----------------------------------------------------------

    @Test
    fun `login clicked with empty fields should emit snackbar error`() = runTest {
        viewModel.event.test {
            viewModel.onIntent(LoginViewModel.Intent.LoginClicked)

            val event = awaitItem()
            assertThat(event).isInstanceOf(BaseUIEvent.ShowSnackbar::class.java)
            assertThat((event as BaseUIEvent.ShowSnackbar).message)
                .isEqualTo("Email and password required")
        }
    }

    @Test
    fun `invalid email should set errorEmail true`() = runTest {
        viewModel.onIntent(LoginViewModel.Intent.EmailChanged("invalid"))
        viewModel.onIntent(LoginViewModel.Intent.PasswordChanged("1234"))

        viewModel.onIntent(LoginViewModel.Intent.LoginClicked)

        assertThat(viewModel.state.value.errorEmail).isTrue()
    }

    @Test
    fun `invalid password should set errorPassword true`() = runTest {
        viewModel.onIntent(LoginViewModel.Intent.EmailChanged("test@gmail.com"))
        viewModel.onIntent(LoginViewModel.Intent.PasswordChanged("12"))

        viewModel.onIntent(LoginViewModel.Intent.LoginClicked)

        assertThat(viewModel.state.value.errorPassword).isTrue()
    }

    // ----------------------------------------------------------
    // LOGIN API CALL TESTS
    // ----------------------------------------------------------

    @Test
    fun `successful login should update state and emit LoginSuccess event`() = runTest {
        // Arrange
        val mockResponse = UserData(
            id = "1",
            name = "Naimish Trivedi",
            email = "naimishtrivedi7@gmail.com",
            mobile = "0123456789"
        )
        coEvery { loginUseCase("naimishtrivedi7@gmail.com", "1234") } returns Resource.Success(mockResponse)

        // Input valid fields
        viewModel.onIntent(LoginViewModel.Intent.EmailChanged("naimishtrivedi7@gmail.com"))
        viewModel.onIntent(LoginViewModel.Intent.PasswordChanged("1234"))

        viewModel.event.test {
            // Act
            viewModel.onIntent(LoginViewModel.Intent.LoginClicked)

            // Assert state
            assertThat(viewModel.state.value.isLoading).isFalse()
            assertThat(viewModel.state.value.loginResponse).isEqualTo(mockResponse)

            // Assert event
            val event = awaitItem()
            assertThat(event).isEqualTo(LoginEvent.LoginSuccess)
        }
    }

    @Test
    fun `login error should emit snackbar and stop loading`() = runTest {
        coEvery { loginUseCase("user@gmail.com", "1234") } returns
                Resource.Error("Login failed")

        viewModel.onIntent(LoginViewModel.Intent.EmailChanged("user@gmail.com"))
        viewModel.onIntent(LoginViewModel.Intent.PasswordChanged("1234"))

        viewModel.event.test {
            viewModel.onIntent(LoginViewModel.Intent.LoginClicked)

            assertThat(viewModel.state.value.isLoading).isFalse()

            val event = awaitItem()
            assertThat(event).isInstanceOf(BaseUIEvent.ShowSnackbar::class.java)
            assertThat((event as BaseUIEvent.ShowSnackbar).message)
                .isEqualTo("Login failed")
        }
    }

    // ----------------------------------------------------------
    // ForgotPassword Clicked TESTS
    // ----------------------------------------------------------

    @Test
    fun `forgot password clicked should emit NavigateToForgotPassword event`() = runTest {
        viewModel.event.test {
            viewModel.onIntent(LoginViewModel.Intent.ForgotPasswordClicked)

            val event = awaitItem()
            assertThat(event).isInstanceOf(LoginEvent.NavigateToForgotPassword::class.java)
        }
    }

    // ----------------------------------------------------------
    // Sign Up Clicked TESTS
    // ----------------------------------------------------------

    @Test
    fun `sign up clicked should emit NavigateToSignUp event`() = runTest {
        viewModel.event.test {
            viewModel.onIntent(LoginViewModel.Intent.SignUpClicked)

            val event = awaitItem()
            assertThat(event).isInstanceOf(LoginEvent.NavigateToSignUp::class.java)
        }
    }
}