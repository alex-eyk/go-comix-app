package com.team.seven.gocomix.domaim.interactor

import android.graphics.Bitmap
import com.team.seven.gocomix.domaim.Result

interface TranslateRecognizedUseCase {

    suspend fun execute(image: Bitmap): Result<List<String>>
}
