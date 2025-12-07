package com.example.composemvidemo.data

abstract class BaseRepository {
    protected suspend fun <T> safeApiCall(apiCall: suspend () -> T): T {
        return try {
            apiCall()
        } catch (e: Exception) {
            throw e
        }
    }

}