package com.team.seven.gocomix.data.repo.comic

import com.team.seven.gocomix.data.entity.Comic
import com.team.seven.gocomix.util.Either

interface FavouriteComicsRepository {

    suspend fun getAll(): Either<List<Comic>>

    suspend fun save(comic: Comic)

    suspend fun delete(comic: Comic)
}
