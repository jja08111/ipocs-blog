package com.foundy.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foundy.domain.usecase.user.GetAllUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllUsersUseCase: GetAllUsersUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchUserList()
    }

    private var fetchJob: Job? = null

    private fun fetchUserList() {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
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
}