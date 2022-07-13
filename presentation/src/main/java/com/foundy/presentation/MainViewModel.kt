package com.foundy.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foundy.domain.usecase.notice.CanWriteNoticeUseCase
import com.foundy.domain.usecase.user.GetAllUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllUsersUseCase: GetAllUsersUseCase,
    private val canWriteNoticeUseCase: CanWriteNoticeUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchUserList()
        fetchCanUserWriteNotice(Random(11212).nextInt(2))
    }

    private var fetchUserListJob: Job? = null
    private var fetchCanUserWriteNoticeJob: Job? = null

    private fun fetchUserList() {
        fetchUserListJob?.cancel()
        fetchUserListJob = viewModelScope.launch {
            val result = getAllUsersUseCase()
            _uiState.update {
                if (result.isSuccess) {
                    it.copy(users = result.getOrNull()!!)
                } else {
                    it.copy(error = result.exceptionOrNull()!!)
                }
            }
        }
    }

    private fun fetchCanUserWriteNotice(userId: Int) {
        fetchCanUserWriteNoticeJob?.cancel()
        fetchCanUserWriteNoticeJob = viewModelScope.launch {
            val result = canWriteNoticeUseCase(userId)
            _uiState.update {
                if (result.isSuccess) {
                    it.copy(canUserWriteNotice = result.getOrNull()!!)
                } else {
                    it.copy(error = result.exceptionOrNull()!!)
                }
            }
        }
    }
}