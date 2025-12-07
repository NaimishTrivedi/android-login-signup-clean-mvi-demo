package com.example.composemvidemo.util

object Validators {
    private val emailRegex =
        Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")

    fun isValidEmail(email: String): Boolean {
        return emailRegex.matches(email)
    }
}