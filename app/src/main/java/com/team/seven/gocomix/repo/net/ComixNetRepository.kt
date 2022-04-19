package com.team.seven.gocomix.repo.net

import com.team.seven.gocomix.model.Comix
import com.team.seven.gocomix.model.Page
import com.team.seven.gocomix.repo.ComixRepository

class ComixNetRepository : ComixRepository {

    override suspend fun getComics(): Result<List<Comix>> {
        return try {
            Result.success(RetrofitInstance.comixApi.getComix())
        } catch (e: Throwable) {
            Result.failure(e)
        }

    }

    override suspend fun getPages(comixId: Int): Result<List<Page>> {
        return try {
            Result.success(RetrofitInstance.comixApi.getPages(comixId))
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }
}


