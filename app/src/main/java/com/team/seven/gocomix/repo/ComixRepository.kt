package com.team.seven.gocomix.repo

import com.team.seven.gocomix.model.Comix
import com.team.seven.gocomix.model.Page

@Deprecated("Use ComicRepository instead")
interface ComixRepository {

    suspend fun getComics(): Result<List<Comix>>

    suspend fun getPages(comixId: Int): Result<List<Page>>
}
