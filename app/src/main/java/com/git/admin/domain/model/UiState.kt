package com.git.admin.domain.model

/** Created by Tony on 12/20/2024. */

sealed class UiState<out T> {
    data object None : UiState<Nothing>()

    data object Loading : UiState<Nothing>()

    data class Success<out T>(val data: T) : UiState<T>()

    data class Error(
        val errorMessage: String,
        val errorCode: String? = null
    ) : UiState<Nothing>()
}