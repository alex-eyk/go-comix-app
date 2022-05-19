package com.team.seven.gocomix.domaim.service

import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions
import com.team.seven.gocomix.domaim.Result
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class LocalTranslateService() : TranslateService {
    private val options = TranslatorOptions.Builder()
        .setSourceLanguage(TranslateLanguage.ENGLISH)
        .setTargetLanguage(TranslateLanguage.RUSSIAN)
        .build()

    private val englishRussianTranslator = Translation.getClient(options)

    private val conditions = DownloadConditions.Builder()
        .requireWifi()
        .build()
    private val translator = Translation.getClient(options)

    override suspend fun translateIntoRussian(
        textBlock: String
    ): Result<String> = suspendCoroutine {
        var downloaded = false
        englishRussianTranslator.downloadModelIfNeeded(conditions)
            .addOnSuccessListener {
                downloaded = true
            }
            .addOnFailureListener { exception ->
                it.resume(Result.Failure(exception))
            }
        if (downloaded) {
            englishRussianTranslator.translate(textBlock)
                .addOnSuccessListener { translatedText ->
                    it.resume(Result.Success(translatedText))
                }
                .addOnFailureListener { exception ->
                    it.resume(Result.Failure(exception))
                }
        }
    }
}
