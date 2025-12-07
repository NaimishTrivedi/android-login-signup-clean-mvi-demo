package com.example.composemvidemo.ui.features

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composemvidemo.ui.features.dashboard.DashBoardScreen
import com.example.composemvidemo.ui.features.login.LoginScreen
import com.example.composemvidemo.ui.features.signup.SignupScreen
import com.example.composemvidemo.ui.features.splash.SplashScreen
import com.example.composemvidemo.ui.theme.ComposeLearningTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MVIDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeLearningTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val viewModel: MVIDemoViewModel = hiltViewModel()
    val isLoggedIn = viewModel.isLoggedIn.collectAsState()
    val splashDone = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        val startTime = System.currentTimeMillis()

        val elapsed = System.currentTimeMillis() - startTime
        val minTime = 1200L   // 1.2 seconds splash

        if (elapsed < minTime) {
            delay(minTime - elapsed)
        }

        splashDone.value = true
    }

    // 🔵 Still loading — show splash
    if (!splashDone.value) {
        SplashScreen()
        return
    }

    // 🔵 Decide start destination
    val startDestination = if (isLoggedIn.value == true) "dashboard" else "login"

    NavHost(navController = navController, startDestination = startDestination) {
        composable("login") {
            LoginScreen(
                onNavigateToSignUp = { navController.navigate("signup") },
                onNavigateToForgotPassword = { navController.navigate("forgot") },
                onNavigateToDashboard = {
                    navController.navigate("dashboard") {
                        popUpTo("login") { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }
        composable("signup") {
            SignupScreen(
                onNavigateToLogin = {
                    navController.navigate("login") {
                        popUpTo("signup") { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }         // Implement similar MVI pattern
        composable("forgot") { } // Implement similar MVI pattern
        composable("dashboard") {
            DashBoardScreen(
                onLogout = {
                    navController.navigate("login") {
                        popUpTo("dashboard") { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }   // Implement similar MVI pattern
    }
}