package com.team.seven.gocomix.repo.net

import com.team.seven.gocomix.model.Comix
import com.team.seven.gocomix.model.Page
import com.team.seven.gocomix.repo.ComixRepository
import javax.inject.Inject

@Deprecated("Use ComicRemoteRepository instead")
class ComixNetRepository @Inject constructor(
    private val comixService: ComixService
) : ComixRepository {

    override suspend fun getComics(): Result<List<Comix>> {
        return try {
            Result.success(comixService.getComix())
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }

    override suspend fun getPages(comixId: Int): Result<List<Page>> {
        return try {
            Result.success(comixService.getPages(comixId))
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }
}
