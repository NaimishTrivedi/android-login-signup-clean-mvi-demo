package com.example.composemvidemo.di

import com.example.composemvidemo.data.repository.LoginRepositoryImpl
import com.example.composemvidemo.data.repository.SessionRepositoryImpl
import com.example.composemvidemo.data.repository.SignupRepositoryImpl
import com.example.composemvidemo.domain.repository.LoginRepository
import com.example.composemvidemo.domain.repository.SessionRepository
import com.example.composemvidemo.domain.repository.SignupRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindLoginRepository(
        impl: LoginRepositoryImpl
    ): LoginRepository

    @Binds
    @Singleton
    abstract fun bindSignupRepository(
        impl: SignupRepositoryImpl
    ): SignupRepository

    @Binds
    abstract fun bindSessionRepository(
        impl: SessionRepositoryImpl
    ): SessionRepository
}