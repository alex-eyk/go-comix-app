package com.team.seven.gocomix.data.repo.image

import android.graphics.Bitmap
import com.team.seven.gocomix.util.Either
import com.team.seven.gocomix.util.EmptyEither

interface ImageLocalRepository {

    suspend fun getImage(id: String): Either<Bitmap>

    suspend fun saveImage(id: String, image: Bitmap): EmptyEither

    fun exists(id: String): Boolean
}
