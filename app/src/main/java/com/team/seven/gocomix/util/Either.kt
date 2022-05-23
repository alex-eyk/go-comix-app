package com.team.seven.gocomix.util

sealed class Either<out T : Any> {

    data class Success<out T : Any>(val value: T) : Either<T>()
    data class Failure(val e: Exception) : Either<Nothing>()
}
