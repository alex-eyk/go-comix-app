package com.team.seven.gocomix.data.repo.comic.impl

import com.team.seven.gocomix.data.entity.Comic
import com.team.seven.gocomix.data.entity.Page
import com.team.seven.gocomix.data.net.ComicService
import com.team.seven.gocomix.data.repo.comic.ComicsRepository
import com.team.seven.gocomix.data.repo.exception.EmptyResponseBodyException
import com.team.seven.gocomix.data.repo.exception.ServerException
import com.team.seven.gocomix.util.Either
import retrofit2.Response
import javax.inject.Inject

class ComicsRemoteRepository @Inject constructor(
    private val comicService: ComicService
) : ComicsRepository {

    companion object {
        private const val INTERNAL_SERVER_ERROR = 500
    }

    override suspend fun getRecommendedComics(): Either<List<Comic>> =
        handleResponse(comicService.getRecommendedComics())

    override suspend fun getPages(comicId: Int): Either<List<Page>> =
        handleResponse(comicService.getPages(comicId))

    private fun <T : Any> handleResponse(response: Response<T>): Either<T> {
        return when {
            response.isSuccessful -> {
                val body = response.body()
                when {
                    body != null -> Either.Success(body)
                    else -> Either.Failure(EmptyResponseBodyException())
                }
            }
            else -> handleError(response.code())
        }
    }

    private fun handleError(code: Int): Either.Failure = when (code) {
        INTERNAL_SERVER_ERROR -> Either.Failure(ServerException())
        else -> Either.Failure(IllegalStateException())
    }
}
