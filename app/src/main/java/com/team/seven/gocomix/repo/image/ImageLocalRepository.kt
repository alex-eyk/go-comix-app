package com.team.seven.gocomix.repo.image

import android.graphics.Bitmap

interface ImageLocalRepository : ImageRepository {

    suspend fun saveImage(id: Long, image: Bitmap): SaveResult

    fun exists(id: Long): Boolean
}

sealed class SaveResult {

    object Loading : SaveResult()
    object Success : SaveResult()
    data class Failure(val error: Throwable) : SaveResult()
}

