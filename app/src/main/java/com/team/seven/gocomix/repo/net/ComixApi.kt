package com.team.seven.gocomix.repo.net

import com.team.seven.gocomix.model.Comix
import com.team.seven.gocomix.model.Page
import retrofit2.http.GET
import retrofit2.http.Path

interface ComixApi {
    @GET("comix/all")
    suspend fun getComix(): List<Comix>

    @GET("comix/pages/{id}")
    suspend fun getPages(@Path("id") id: Int): List<Page>
}
