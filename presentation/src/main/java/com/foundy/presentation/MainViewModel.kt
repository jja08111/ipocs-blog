package com.foundy.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foundy.domain.usecase.GetAllUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllUsersUseCase: GetAllUsersUseCase
): ViewModel() {

    private val _userListState = MutableStateFlow<MainUiState>(MainUiState.Loading)
    val userListState = _userListState.asStateFlow()

    init {
        fetchUserList()
    }

    private fun fetchUserList() = viewModelScope.launch {
        try {
            val result = getAllUsersUseCase()
            if (result.isSuccess) {
                _userListState.value = MainUiState.Success(result.getOrNull()!!)
            } else {
                throw result.exceptionOrNull()!!
            }
        } catch (e: Exception) {
            _userListState.value = MainUiState.Error(e)
        }
    }
}