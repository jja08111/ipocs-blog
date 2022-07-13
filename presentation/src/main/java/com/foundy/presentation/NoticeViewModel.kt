package com.foundy.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foundy.domain.usecase.notice.GetAllNoticesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoticeViewModel @Inject constructor(
    private val getAllNoticesUseCase: GetAllNoticesUseCase
): ViewModel() {

    private val _noticeListState = MutableStateFlow<NoticeUiState>(NoticeUiState.Loading)
    val noticeListState = _noticeListState.asStateFlow()

    init {
        fetchNoticeList()
    }

    private fun fetchNoticeList() = viewModelScope.launch {
        try {
            val result = getAllNoticesUseCase()
            if (result.isSuccess) {
                _noticeListState.value = NoticeUiState.Success(result.getOrNull()!!)
            } else {
                throw result.exceptionOrNull()!!
            }
        } catch (e: Exception) {
            _noticeListState.value = NoticeUiState.Error(e)
        }
    }
}