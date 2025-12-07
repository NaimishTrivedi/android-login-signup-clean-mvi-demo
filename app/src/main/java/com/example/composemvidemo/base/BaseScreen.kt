package com.example.composemvidemo.base

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.composemvidemo.events.BaseUIEvent
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest

@Composable
fun <E: BaseUIEvent> BaseScreen(
    isLoading: Boolean = false,
    topBar: @Composable (() -> Unit)? = null,
    stateContent: @Composable () -> Unit,
    eventFlow: SharedFlow<E>? = null,
    onEvent: (E) -> Unit = {}
) {
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = eventFlow) {
        eventFlow?.collectLatest { event ->
            when(event){
                is BaseUIEvent.ShowToast -> {

                }
                is BaseUIEvent.ShowSnackbar -> snackbarHostState.showSnackbar(event.message)
                else -> onEvent(event)
            }
        }
    }

    Scaffold(
        topBar = {
            topBar?.invoke()
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            stateContent()
        }
    }

    // Full-screen loading overlay
    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f)),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}