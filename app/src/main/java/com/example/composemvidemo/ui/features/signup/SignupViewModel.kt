package com.example.composemvidemo.ui.features.signup

import androidx.lifecycle.viewModelScope
import com.example.composemvidemo.base.BaseViewModel
import com.example.composemvidemo.data.remote.dto.SignupRequest
import com.example.composemvidemo.domain.model.Resource
import com.example.composemvidemo.domain.usecase.SignupUseCase
import com.example.composemvidemo.events.BaseUIEvent
import com.example.composemvidemo.events.SignupEvent
import com.example.composemvidemo.util.Validators
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val signupUseCase: SignupUseCase
) : BaseViewModel<SignupUiState, SignupViewModel.Intent, BaseUIEvent>(SignupUiState()) {
    sealed class Intent {
        data class NameChanged(val value: String) : Intent()
        data class EmailChanged(val value: String) : Intent()
        data class MobileChanged(val value: String) : Intent()
        data class PasswordChanged(val value: String) : Intent()
        data class CpasswordChanged(val value: String) : Intent()
        object LoginClicked : Intent()
        object SignUpClicked : Intent()
    }

    override fun onIntent(intent: Intent) {
        when (intent) {
            is Intent.NameChanged -> setState { copy(name = intent.value, errorName = false) }
            is Intent.EmailChanged -> setState { copy(email = intent.value, errorEmail = false) }
            is Intent.MobileChanged -> setState { copy(mobile = intent.value, errorMobile = false) }
            is Intent.PasswordChanged -> setState {
                copy(
                    password = intent.value,
                    errorPassword = false
                )
            }
            is Intent.CpasswordChanged -> setState { copy(cpassword = intent.value, errorCpassword = false) }
            is Intent.LoginClicked -> sendEvent(SignupEvent.NavigateToLogin)
            is Intent.SignUpClicked -> doSignup()
        }
    }

    private fun doSignup() {
        val name = _state.value.name
        val email = _state.value.email
        val mobile = _state.value.mobile
        val password = _state.value.password
        val cpassword = _state.value.cpassword

        if (name.isBlank()) {
            setState { copy(errorName = true) }
            return
        }

        if (email.isBlank()) {
            setState { copy(errorEmail = true) }
            return
        }

        val validateEmail = !Validators.isValidEmail(email)
        if (validateEmail) {
            setState { copy(errorEmail = true) }
            return
        }

        if (mobile.isBlank()) {
            setState { copy(errorMobile = true) }
            return
        }

        if (mobile.length != 10) {
            setState { copy(errorMobile = true) }
            return
        }

        if (password.isBlank()) {
            setState { copy(errorPassword = true) }
            return
        }

        val validatePassword = password.length !in 4..8
        if (validatePassword) {
            setState { copy(errorPassword = true) }
            return
        }

        if (cpassword.isBlank()) {
            setState { copy(errorCpassword = true) }
            return
        }

        if (password != cpassword) {
            setState { copy(errorCpassword = true) }
            return
        }

        viewModelScope.launch {
            setState { copy(isLoading = true) }
            when (val result = signupUseCase(
                SignupRequest(
                    name = name,
                    email = email,
                    mobile = mobile,
                    password = password
                )
            )) {
                is Resource.Success -> {
                    setState { copy(isLoading = false, loginResponse = result.data) }
                    sendEvent(SignupEvent.SignupSuccess)
                }

                is Resource.Error -> {
                    setState { copy(isLoading = false) }
                    sendEvent(BaseUIEvent.ShowSnackbar(result.message))
                }
            }
        }
    }


}