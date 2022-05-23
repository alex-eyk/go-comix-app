package com.team.seven.gocomix.domaim.interactor

import android.graphics.Bitmap
import com.team.seven.gocomix.domaim.service.RecognizeService
import com.team.seven.gocomix.domaim.service.TranslateService
import com.team.seven.gocomix.util.Either

class TranslateRecognizeUseCaseImpl(
    private val recognizer: RecognizeService,
    private val translator: TranslateService
) : TranslateRecognizeUseCase {

    override suspend fun execute(image: Bitmap): Either<List<String>> {
        return when (val state = recognizer.recognize(image)) {
            is Either.Success -> {
                translateBlocks(state.value)
            }
            is Either.Failure -> {
                state
            }
        }
    }

    private suspend fun translateBlocks(blocks: List<String>): Either<List<String>> {
        val translatedBlocks = arrayListOf<String>()
        blocks.forEach {
            when (val translateState = translator.translateIntoRussian(it)) {
                is Either.Success -> {
                    translatedBlocks.add(translateState.value)
                }
                is Either.Failure -> {
                    return translateState
                }
            }
        }
        return Either.Success(translatedBlocks)
    }
}
