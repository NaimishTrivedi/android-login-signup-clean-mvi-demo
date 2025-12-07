package com.example.composemvidemo.ui.features.login

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.composemvidemo.base.BaseScreen
import com.example.composemvidemo.events.LoginEvent

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onNavigateToSignUp: () -> Unit,
    onNavigateToForgotPassword: () -> Unit,
    onNavigateToDashboard: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
    BaseScreen(
        isLoading = state.isLoading,
        stateContent = {
            LoginContent(
                state = state,
                onEmailChanged = { viewModel.onIntent(LoginViewModel.Intent.EmailChanged(it)) },
                onPasswordChanged = { viewModel.onIntent(LoginViewModel.Intent.PasswordChanged(it)) },
                onLoginClick = { viewModel.onIntent(LoginViewModel.Intent.LoginClicked) },
                onForgotPasswordClick = { viewModel.onIntent(LoginViewModel.Intent.ForgotPasswordClicked) },
                onSignUpClick = { viewModel.onIntent(LoginViewModel.Intent.SignUpClicked) }
            )
        },
        eventFlow = viewModel.event,
        onEvent = { event ->
            when (event) {
                is LoginEvent.NavigateToSignUp -> onNavigateToSignUp()
                is LoginEvent.NavigateToForgotPassword -> onNavigateToForgotPassword()
                is LoginEvent.LoginSuccess -> {
                    Toast.makeText(context,"Login successfully.", Toast.LENGTH_SHORT).show()
                    onNavigateToDashboard()
                }
                else -> {}
            }
        }
    )
}