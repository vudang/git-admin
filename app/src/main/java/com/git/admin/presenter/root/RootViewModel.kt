package com.git.admin.presenter.root

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.git.admin.domain.model.UiState
import com.git.admin.domain.stream.user.MutableUserStream
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * The ViewModel for [RootScreen].
 * Fetch the current user from data layer and update the UI state based on the fetched data.
 * TODO: We can add more business logic here like fetch app's config, use data before load the the main app.
 *
 * @param authenticatedStream The [MutableUserStream] to fetch the current user.
 */
@HiltViewModel
class RootViewModel @Inject constructor(
    private val authenticatedStream: MutableUserStream
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Boolean>> = MutableStateFlow(UiState.None)
    val uiState: MutableStateFlow<UiState<Boolean>> = _uiState

    init {
        loadCurrentAuthState()
    }

    /**
     * TODO: Add more business logic here like fetch app's config, use data before load the the main app.
     * This is just a placeholder to fetch the current user auth state.
     */
    private fun loadCurrentAuthState() {
        _uiState.value = UiState.Loading
        viewModelScope.launch {
            _uiState.value = UiState.Success(true)
        }
    }
}