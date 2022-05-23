package com.team.seven.gocomix.ui

import com.team.seven.gocomix.util.Either

sealed class UiState<out T : Any> {

    object None : UiState<Nothing>()
    object Loading : UiState<Nothing>()
    data class Success<out T : Any>(val value: T) : UiState<T>()
    data class Failure(val e: Exception) : UiState<Nothing>()

    companion object {
        fun <T : Any> by(result: Either<T>): UiState<T> {
            return when (result) {
                is Either.Success -> Success(result.value)
                is Either.Failure -> Failure(result.e)
            }
        }
    }
}
