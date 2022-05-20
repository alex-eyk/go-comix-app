package com.team.seven.gocomix.data.repo

import android.graphics.Bitmap

interface ImageLocalRepository : ImageRepository {

    suspend fun saveImage(id: String, image: Bitmap): SaveResult

    fun exists(id: String): Boolean
}

sealed class SaveResult {

    object Loading : SaveResult()
    object Success : SaveResult()
    data class Failure(val error: Throwable) : SaveResult()
}
