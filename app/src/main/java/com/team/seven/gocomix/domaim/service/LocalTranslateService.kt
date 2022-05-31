package com.team.seven.gocomix.domaim.service

import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.Translator
import com.google.mlkit.nl.translate.TranslatorOptions
import com.team.seven.gocomix.util.Either
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class LocalTranslateService : TranslateService {

    private val englishRussianTranslator: Translator

    init {
        val options = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.ENGLISH)
            .setTargetLanguage(TranslateLanguage.RUSSIAN)
            .build()
        this.englishRussianTranslator = Translation.getClient(options)
    }

    override suspend fun translateIntoRussian(
        text: String
    ): Either<String> {
        return when (val result = downloadModel()) {
            is Either.Success -> {
                translate(text)
            }
            is Either.Failure -> {
                result
            }
        }
    }

    private suspend fun downloadModel(): Either<Boolean> = suspendCoroutine {
        val downloadConditions = DownloadConditions.Builder()
            .requireWifi()
            .build()
        englishRussianTranslator.downloadModelIfNeeded(downloadConditions)
            .addOnSuccessListener { _ ->
                it.resume(Either.Success(true))
            }
            .addOnFailureListener { exception ->
                it.resume(Either.Failure(exception))
            }
    }

    private suspend fun translate(text: String): Either<String> = suspendCoroutine {
        englishRussianTranslator.translate(text)
            .addOnSuccessListener { translatedText ->
                it.resume(Either.Success(translatedText))
            }
            .addOnFailureListener { exception ->
                it.resume(Either.Failure(exception))
            }
    }
}
