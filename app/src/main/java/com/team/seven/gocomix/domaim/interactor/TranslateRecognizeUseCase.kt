package com.team.seven.gocomix.domaim.interactor

import android.graphics.Bitmap
import com.team.seven.gocomix.util.Either

interface TranslateRecognizeUseCase {

    suspend fun execute(image: Bitmap): Either<String>
}
