package com.team.seven.gocomix.domaim.service

import android.graphics.Bitmap
import com.team.seven.gocomix.util.Either

interface RecognizeService {

    suspend fun recognize(image: Bitmap): Either<List<String>>
}
