package com.foundy.presentation

import com.foundy.domain.model.Notice

sealed class NoticeUiState {
    class Success(val notices: List<Notice>) : NoticeUiState()
    class Error(val exception: Exception) : NoticeUiState()
    object Loading : NoticeUiState()
}
