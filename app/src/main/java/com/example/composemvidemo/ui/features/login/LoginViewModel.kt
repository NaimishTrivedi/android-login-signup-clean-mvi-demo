package com.example.composemvidemo.ui.features.login

import androidx.lifecycle.viewModelScope
import com.example.composemvidemo.base.BaseViewModel
import com.example.composemvidemo.domain.model.Resource
import com.example.composemvidemo.domain.usecase.LoginUseCase
import com.example.composemvidemo.events.BaseUIEvent
import com.example.composemvidemo.events.LoginEvent
import com.example.composemvidemo.util.Validators
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : BaseViewModel<LoginUiState, LoginViewModel.Intent, BaseUIEvent>(LoginUiState()) {
    sealed class Intent {
        data class EmailChanged(val value: String) : Intent()
        data class PasswordChanged(val value: String) : Intent()
        object LoginClicked : Intent()
        object ForgotPasswordClicked : Intent()
        object SignUpClicked : Intent()
    }

    override fun onIntent(intent: Intent) {
        when (intent) {
            is Intent.EmailChanged ->  setState { copy(email = intent.value,errorEmail = false) }
            is Intent.PasswordChanged -> setState { copy(password = intent.value, errorPassword = false) }
            is Intent.LoginClicked -> doLogin()
            is Intent.ForgotPasswordClicked -> sendEvent(LoginEvent.NavigateToForgotPassword)
            is Intent.SignUpClicked -> sendEvent(LoginEvent.NavigateToSignUp)
        }
    }

    private fun doLogin() {
        val email = _state.value.email
        val password = _state.value.password

        if (email.isBlank() || password.isBlank()) {
            sendEvent(BaseUIEvent.ShowSnackbar("Email and password required"))
            return
        }

        val validateEmail = !Validators.isValidEmail(email)
        if(validateEmail){
            setState { copy(errorEmail = true) }
            return
        }

        val validatePassword = password.length !in 4..8
        if(validatePassword){
            setState { copy(errorPassword = true) }
            return
        }

        viewModelScope.launch {
            setState { copy(isLoading = true) }
            when (val result = loginUseCase(email, password)) {
                is Resource.Success -> {
                    setState { copy(isLoading = false, loginResponse = result.data) }
                    sendEvent(LoginEvent.LoginSuccess)
                }

                is Resource.Error -> {
                    setState { copy(isLoading = false) }
                    sendEvent(BaseUIEvent.ShowSnackbar(result.message))
                }
            }
        }
    }


}