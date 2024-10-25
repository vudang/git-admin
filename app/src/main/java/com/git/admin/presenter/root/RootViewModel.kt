package com.git.admin.presenter.root

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.git.admin.domain.model.UiState
import com.git.admin.domain.stream.user.MutableUserStream
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RootViewModel @Inject constructor(
    private val authenticatedStream: MutableUserStream
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Boolean>> = MutableStateFlow(UiState.None)
    val uiState: MutableStateFlow<UiState<Boolean>> = _uiState

    init {
        loadCurrentUser()
    }

    private fun loadCurrentUser() {
        _uiState.value = UiState.Loading
        viewModelScope.launch {
            _uiState.value = UiState.Success(true)
        }
    }
}