package com.example.composemvidemo.base

import androidx.lifecycle.ViewModel
import com.example.composemvidemo.events.BaseUIEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<STATE,INTENT, EVENT: BaseUIEvent>(initialState: STATE) : ViewModel() {

    private val vmScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    protected val _state = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<BaseUIEvent>()
    val event = _event.asSharedFlow()

    protected fun setState(reducer: STATE.() -> STATE) {
        _state.update { it.reducer() }
    }

    protected fun sendEvent(event: EVENT) {
        vmScope.launch { _event.emit(event) }
    }

    /**
     * Every ViewModel must implement this.
     * Single entry point for all UI Intents.
     */
    abstract fun onIntent(intent: INTENT)

    override fun onCleared() {
        super.onCleared()
        vmScope.cancel()
    }
}