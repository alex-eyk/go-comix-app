package com.team.seven.gocomix.data.repo.comic.impl

import com.team.seven.gocomix.data.dao.FavouriteComicsDao
import com.team.seven.gocomix.data.entity.Comic
import com.team.seven.gocomix.data.repo.comic.FavouriteComicsRepository
import com.team.seven.gocomix.util.Either
import javax.inject.Inject

class FavouriteComicsLocalRepository @Inject constructor(
    private val comicsDao: FavouriteComicsDao
) : FavouriteComicsRepository {

    override suspend fun getAll(): Either<List<Comic>> {
        return Either.Success(comicsDao.getAll())
    }

    override suspend fun save(comic: Comic) {
        comicsDao.insert(comic)
    }

    override suspend fun delete(comic: Comic) {
        comicsDao.delete(comic)
    }
}
