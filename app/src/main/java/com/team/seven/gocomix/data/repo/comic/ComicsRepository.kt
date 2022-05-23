package com.team.seven.gocomix.data.repo.comic

import com.team.seven.gocomix.data.entity.Comic
import com.team.seven.gocomix.data.entity.Page
import com.team.seven.gocomix.util.Either

interface ComicsRepository {

    suspend fun getRecommendedComics(): Either<List<Comic>>

    suspend fun getPages(comicId: Int): Either<List<Page>>
}
