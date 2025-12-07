package com.example.composemvidemo.ui.features.dashboard

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.composemvidemo.base.BaseScreen
import com.example.composemvidemo.events.BaseUIEvent

@Composable
fun DashBoardScreen(
    viewModel: DashboardViewModel = hiltViewModel(),
    onLogout: () -> Unit
) {
    val name by viewModel.userName.collectAsState()
    val context = LocalContext.current
    BaseScreen<BaseUIEvent>(
        topBar = { DashboardTopBar(userName = name, onLogout = {
            viewModel.logout()
            Toast.makeText(context,"Bye! See you again.", Toast.LENGTH_SHORT).show()
            onLogout()
        }) },
        stateContent = {
            DashBoardContent()
        }
    )
}