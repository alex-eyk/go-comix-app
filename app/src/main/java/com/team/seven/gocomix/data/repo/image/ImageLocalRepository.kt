package com.team.seven.gocomix.data.repo.image

import android.graphics.Bitmap

interface ImageLocalRepository {

    suspend fun getImage(id: String): ImageResult

    suspend fun saveImage(id: String, image: Bitmap): SaveResult

    fun exists(id: String): Boolean
}

sealed class SaveResult {

    object Loading : SaveResult()
    object Success : SaveResult()
    data class Failure(val error: Throwable) : SaveResult()
}
