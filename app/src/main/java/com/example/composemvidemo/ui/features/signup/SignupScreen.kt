package com.example.composemvidemo.ui.features.signup

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.composemvidemo.base.BaseScreen
import com.example.composemvidemo.events.SignupEvent

@Composable
fun SignupScreen(
    viewModel: SignupViewModel = hiltViewModel(),
    onNavigateToLogin: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
    BaseScreen(
        isLoading = state.isLoading,
        stateContent = {
            SignupContent(
                state = state,
                onNameChanged = { viewModel.onIntent(SignupViewModel.Intent.NameChanged(it)) },
                onEmailChanged = { viewModel.onIntent(SignupViewModel.Intent.EmailChanged(it)) },
                onMobileChanged = { viewModel.onIntent(SignupViewModel.Intent.MobileChanged(it)) },
                onPasswordChanged = { viewModel.onIntent(SignupViewModel.Intent.PasswordChanged(it)) },
                onCPasswordChanged = { viewModel.onIntent(SignupViewModel.Intent.CpasswordChanged(it)) },
                onSignupClick = { viewModel.onIntent(SignupViewModel.Intent.SignUpClicked) },
                onLoginClick = { viewModel.onIntent(SignupViewModel.Intent.LoginClicked) }
            )
        },
        eventFlow = viewModel.event,
        onEvent = { event ->
            when (event) {
                is SignupEvent.NavigateToLogin -> onNavigateToLogin()
                is SignupEvent.SignupSuccess -> {
                    Toast.makeText(context, "Signup successfully.", Toast.LENGTH_SHORT).show()
                    onNavigateToLogin()
                }

                else -> {}
            }
        }
    )
}