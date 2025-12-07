package com.example.composemvidemo.events

sealed class BaseUIEvent {
    data class ShowToast(val message: String) : BaseUIEvent()
    data class ShowSnackbar(val message: String) : BaseUIEvent()
}