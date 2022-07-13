package com.foundy.presentation

import com.foundy.domain.model.Notice

data class NoticeUiState (
    val notices: List<Notice> = emptyList(),
    val error: Throwable? = null,
    val isFetchingNotices: Boolean = false
)
