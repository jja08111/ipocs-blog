package com.foundy.presentation

import com.foundy.domain.model.User

data class MainUiState(
    val users: List<User> = emptyList(),
    val canUserWriteNotice: Boolean = false,
    val error: Throwable? = null,
    val isFetchingUsers: Boolean = false
)