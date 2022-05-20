package com.team.seven.gocomix.data.repo.comic

import com.team.seven.gocomix.data.entity.Comic
import com.team.seven.gocomix.data.entity.Page
import com.team.seven.gocomix.util.Result

interface ComicRepository {

    suspend fun getRecommendedComics(): Result<List<Comic>>

    suspend fun getPages(comicId: Int): Result<List<Page>>
}
