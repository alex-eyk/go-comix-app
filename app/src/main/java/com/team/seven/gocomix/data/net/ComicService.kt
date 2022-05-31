package com.team.seven.gocomix.data.net

import com.team.seven.gocomix.data.entity.Comic
import com.team.seven.gocomix.data.entity.Page
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ComicService {

    @GET("comix/all")
    suspend fun getRecommendedComics(): Response<List<Comic>>

    @GET("comix/pages/{id}")
    suspend fun getPages(
        @Path("id") id: Int
    ): Response<List<Page>>
}
