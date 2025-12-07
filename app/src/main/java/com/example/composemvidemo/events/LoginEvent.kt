package com.example.composemvidemo.events

sealed class LoginEvent : BaseUIEvent() {
    object NavigateToSignUp : LoginEvent()
    object NavigateToForgotPassword : LoginEvent()
    object LoginSuccess : LoginEvent()
}