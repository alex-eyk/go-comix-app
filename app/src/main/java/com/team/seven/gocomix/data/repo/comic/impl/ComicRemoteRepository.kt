package com.team.seven.gocomix.data.repo.comic.impl

import com.team.seven.gocomix.data.entity.Comic
import com.team.seven.gocomix.data.entity.Page
import com.team.seven.gocomix.data.net.ComicService
import com.team.seven.gocomix.data.repo.comic.ComicRepository
import com.team.seven.gocomix.util.Result
import javax.inject.Inject

class ComicRemoteRepository @Inject constructor(
    private val comicService: ComicService
) : ComicRepository {

    override suspend fun getRecommendedComics(): Result<List<Comic>> {
        TODO("Not yet implemented")
    }

    override suspend fun getPages(comicId: Int): Result<List<Page>> {
        TODO("Not yet implemented")
    }
}
