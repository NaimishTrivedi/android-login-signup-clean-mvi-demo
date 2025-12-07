package com.example.composemvidemo.ui.features

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composemvidemo.domain.usecase.MVIDemoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MVIDemoViewModel @Inject constructor(
    private val mviDemoUseCase: MVIDemoUseCase
) : ViewModel() {

    val isLoggedIn = mviDemoUseCase.isLoggedIn()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            null // null means loading state
        )
}