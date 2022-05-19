package com.team.seven.gocomix.domaim.service

import android.graphics.Bitmap
import com.team.seven.gocomix.domaim.Result

interface RecognizeService {

    suspend fun recognize(image: Bitmap): Result<List<String>>
}
