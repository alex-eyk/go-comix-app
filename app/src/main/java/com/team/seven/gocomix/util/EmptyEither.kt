package com.team.seven.gocomix.util

sealed class EmptyEither {

    object Success : EmptyEither()
    data class Failure(val e: Exception) : EmptyEither()
}
