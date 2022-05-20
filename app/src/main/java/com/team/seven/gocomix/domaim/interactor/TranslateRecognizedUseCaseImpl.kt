package com.team.seven.gocomix.domaim.interactor

import android.graphics.Bitmap
import com.team.seven.gocomix.domaim.Result
import com.team.seven.gocomix.domaim.service.RecognizeService
import com.team.seven.gocomix.domaim.service.TranslateService

class TranslateRecognizedUseCaseImpl(
    private val recognizer: RecognizeService,
    private val translator: TranslateService
) : TranslateRecognizedUseCase {

    override suspend fun execute(image: Bitmap): Result<List<String>> {
        return when (val state = recognizer.recognize(image)) {
            is Result.Success -> {
                translateBlocks(state.result)
            }
            is Result.Failure -> {
                state
            }
        }
    }

    private suspend fun translateBlocks(blocks: List<String>): Result<List<String>> {
        val translatedBlocks = arrayListOf<String>()
        blocks.forEach {
            when (val translateState = translator.translateIntoRussian(it)) {
                is Result.Success -> {
                    translatedBlocks.add(translateState.result)
                }
                is Result.Failure -> {
                    return translateState
                }
            }
        }
        return Result.Success(translatedBlocks)
    }
}
