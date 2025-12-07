package com.example.composemvidemo.data.repository

import android.content.Context
import com.example.composemvidemo.domain.repository.SessionRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import androidx.core.content.edit

class SessionRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : SessionRepository {

    private val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    override suspend fun saveLoginData(
        userId: String,
        name: String,
        email: String
    ) {
        prefs.edit {
            putString("name", name)
                .putString("userId", userId)
                .putString("email", email)
        }
    }

    override fun getUserName(): Flow<String> =  flow {
        emit(prefs.getString("name", "") ?: "")
    }

    override suspend fun clearSession() {
        prefs.edit { clear() }
    }

    override fun isLoggedIn(): Flow<Boolean> = flow {
        emit(prefs.getString("userId", null) != null)
    }
}
