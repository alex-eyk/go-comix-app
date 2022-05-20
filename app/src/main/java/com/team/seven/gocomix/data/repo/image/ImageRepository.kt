package com.team.seven.gocomix.data.repo.image

import android.graphics.Bitmap
import com.team.seven.gocomix.data.entity.Quality

interface ImageRepository {

    suspend fun getImage(id: String, quality: Quality): ImageResult
}

sealed class ImageResult {

    object Loading : ImageResult()
    data class Success(val image: Bitmap) : ImageResult()
    data class Failure(val error: Throwable) : ImageResult()
}
