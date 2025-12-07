package com.example.composemvidemo.ui.features.dashboard

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardTopBar(
    userName: String = "",
    onLogout: () -> Unit = {}
) {
    TopAppBar(
        title = { Text("Hi, $userName") },
        actions = {
            TextButton(onClick = onLogout) {
                Text("Logout")
            }
        }
    )
}