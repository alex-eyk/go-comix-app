package com.team.seven.gocomix.repo.image

import android.graphics.BitmapFactory
import com.team.seven.gocomix.repo.exception.ConnectionException
import com.team.seven.gocomix.repo.exception.ImageNotDecodedException
import com.team.seven.gocomix.repo.exception.NoImageFoundException
import com.team.seven.gocomix.repo.net.ImageService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.io.InputStream

class RemoteImageRepository(
    private val imageService: ImageService
) : ImageRepository {

    override suspend fun getImage(id: String): ImageResult {
        return withContext(Dispatchers.IO) {
            val netStream: InputStream
            val result = imageService.getImage(id)
            if (result.isSuccessful) {
                netStream = result.body()?.byteStream()
                    ?: return@withContext ImageResult.Failure(NoImageFoundException())
            } else {
                return@withContext ImageResult.Failure(ConnectionException())
            }
            try {
                val image = BitmapFactory.decodeStream(netStream)
                if (image != null) {
                    return@withContext ImageResult.Success(image)
                } else {
                    return@withContext ImageResult.Failure(ImageNotDecodedException())
                }
            } catch (e: IOException) {
                return@withContext ImageResult.Failure(ImageNotDecodedException())
            }
        }
    }
}
