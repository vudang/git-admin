package com.git.admin.data.model.response

import com.git.admin.data.model.base.APIError
import com.git.admin.domain.model.UiState

sealed class DataResult<out T> {
    data object Loading : DataResult<Nothing>()
    data class Success<out T>(val data: T) : DataResult<T>()
    data class Error(val error: APIError) : DataResult<Nothing>()
    
    fun toUiState(): UiState<T> {
        return when (this) {
            is Success -> UiState.Success(data)
            is Error -> UiState.Error(error.message, error.code)
            is Loading -> UiState.Loading
        }
    }
}
