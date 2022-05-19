package com.team.seven.gocomix.domaim.interactor

import android.graphics.Bitmap
import com.team.seven.gocomix.domaim.Result

class TranslateRecognizedUseCaseImpl() : TranslateRecognizedUseCase {

    override suspend fun execute(image: Bitmap): Result<List<String>> {
        TODO("Not yet implemented")
    }
}
