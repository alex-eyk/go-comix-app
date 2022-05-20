package com.team.seven.gocomix.data.net

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Streaming

interface ImageService {

    @Streaming
    @GET("image/{id}")
    suspend fun getImage(@Path("id") id: String): Response<ResponseBody>
}
