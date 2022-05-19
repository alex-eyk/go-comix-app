package com.team.seven.gocomix.repo.image

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import android.os.Environment.MEDIA_MOUNTED
import android.os.Environment.MEDIA_MOUNTED_READ_ONLY
import android.util.Log
import com.team.seven.gocomix.repo.exception.ImageAlreadyExistsException
import com.team.seven.gocomix.repo.exception.ImageNotDecodedException
import com.team.seven.gocomix.repo.exception.StorageNotReadableException
import com.team.seven.gocomix.repo.exception.StorageNotWritableException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

class LocalImageRepository : ImageLocalRepository {

    companion object {

        private const val TAG = "LocalImageRepository"

        private const val COMICS_FOLDER = "comics"
        private const val IMAGE_EXT = "png"
        private const val QUALITY_MAX = 100
    }

    override suspend fun getImage(id: String): ImageResult {
        if (isStorageReadable() == false) {
            Log.e(TAG, "Storage is not readable")
            return ImageResult.Failure(StorageNotReadableException())
        }
        val imageFile = File(getComicImagesDirectory(), "$id.$IMAGE_EXT")
        if (imageFile.exists() == false) {
            Log.e(TAG, "Image $id.png not found")
            return ImageResult.Failure(FileNotFoundException())
        }
        return withContext(Dispatchers.IO) {
            return@withContext try {
                val image = BitmapFactory.decodeFile(imageFile.absolutePath)
                if (image != null) {
                    ImageResult.Success(image)
                } else {
                    ImageResult.Failure(ImageNotDecodedException())
                }
            } catch (e: Exception) {
                ImageResult.Failure(e)
            }
        }
    }

    @Suppress("BlockingMethodInNonBlockingContext")
    override suspend fun saveImage(id: String, image: Bitmap): SaveResult {
        if (isStorageWritable() == false) {
            Log.e(TAG, "Storage is not writable")
            return SaveResult.Failure(StorageNotWritableException())
        }
        val imageFile = File(getComicImagesDirectory(), "$id.$IMAGE_EXT")
        if (imageFile.exists()) {
            return SaveResult.Failure(ImageAlreadyExistsException())
        }
        return withContext(Dispatchers.IO) {
            try {
                val outputStream = FileOutputStream(imageFile)
                image.compress(Bitmap.CompressFormat.PNG, QUALITY_MAX, outputStream)
                outputStream.flush()
                outputStream.close()
                return@withContext SaveResult.Success
            } catch (e: IOException) {
                return@withContext SaveResult.Failure(e)
            }
        }
    }

    override fun exists(id: String): Boolean {
        if (isStorageReadable() == false) {
            Log.e(TAG, "Storage is not readable")
            return false
        }
        val imageFile = File(getComicImagesDirectory(), "$id.$IMAGE_EXT")
        return imageFile.exists()
    }

    private fun getComicImagesDirectory(): File {
        val file = File(
            Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES
            ), COMICS_FOLDER
        )
        if (file.exists() == false) {
            val createResult = file.mkdir()
            if (createResult == false) {
                Log.e(TAG, "Unable to create comics directory")
            }
        }
        return file
    }

    private fun isStorageReadable(): Boolean {
        val state = Environment.getExternalStorageState()
        return MEDIA_MOUNTED == state || MEDIA_MOUNTED_READ_ONLY == state
    }

    private fun isStorageWritable(): Boolean {
        val state = Environment.getExternalStorageState()
        return MEDIA_MOUNTED == state
    }
}
