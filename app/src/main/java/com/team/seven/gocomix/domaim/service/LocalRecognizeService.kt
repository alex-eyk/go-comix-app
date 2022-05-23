package com.team.seven.gocomix.domaim.service

import android.graphics.Bitmap
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import com.team.seven.gocomix.util.Either
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class LocalRecognizeService : RecognizeService {
    private val recognizer = TextRecognition
        .getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

    override suspend fun recognize(image: Bitmap): Either<List<String>> = suspendCoroutine {
        recognizer.process(InputImage.fromBitmap(image, 0))
            .addOnSuccessListener { text ->
                val textBlocks = text.textBlocks.map { it.text }
                it.resume(Either.Success(textBlocks))
            }
            .addOnFailureListener { exception ->
                it.resume(Either.Failure(exception))
            }
    }
}
