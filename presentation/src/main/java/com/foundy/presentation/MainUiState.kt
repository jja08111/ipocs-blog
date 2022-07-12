package com.foundy.presentation

import com.foundy.domain.model.User

sealed class MainUiState {
    class Success(val users: List<User>) : MainUiState()
    class Error(val exception: Exception) : MainUiState()
    object Loading : MainUiState()
}