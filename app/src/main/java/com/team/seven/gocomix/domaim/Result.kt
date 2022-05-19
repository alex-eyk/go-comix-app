package com.team.seven.gocomix.domaim

sealed class Result<out T : Any> {

    object Loading : Result<Nothing>()
    data class Success<out T : Any>(val result: T) : Result<T>()
    data class Failure(val e: Exception) : Result<Nothing>()
}
