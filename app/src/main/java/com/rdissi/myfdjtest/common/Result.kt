package com.rdissi.myfdjtest.common

sealed class Result<out T : Any> {
    data object Loading : Result<Nothing>()
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val message: String) : Result<Nothing>()
}

inline fun <T : Any> Result<T>.handle(
    onLoading: () -> Unit,
    onSuccess: (T) -> Unit,
    onError: (String?) -> Unit
) {
    when (this) {
        is Result.Loading -> onLoading()
        is Result.Success -> onSuccess(data)
        is Result.Error -> onError(message)
    }
}