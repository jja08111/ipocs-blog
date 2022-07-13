package com.foundy.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foundy.domain.usecase.notice.GetAllNoticesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoticeViewModel @Inject constructor(
    private val getAllNoticesUseCase: GetAllNoticesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(NoticeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchNoticeList()
    }

    private var fetchingJob: Job? = null

    private fun fetchNoticeList() {
        fetchingJob?.cancel()
        fetchingJob = viewModelScope.launch {
            _uiState.update { it.copy(isFetchingNotices = true) }
            val result = getAllNoticesUseCase()
            _uiState.update {
                if (result.isSuccess) {
                    it.copy(notices = result.getOrNull()!!, isFetchingNotices = false)
                } else {
                    it.copy(error = result.exceptionOrNull()!!, isFetchingNotices = false)
                }
            }
        }
    }
}