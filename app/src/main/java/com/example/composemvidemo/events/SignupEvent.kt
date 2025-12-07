package com.example.composemvidemo.events

sealed class SignupEvent : BaseUIEvent() {
    object NavigateToLogin : SignupEvent()
    object SignupSuccess : SignupEvent()
}