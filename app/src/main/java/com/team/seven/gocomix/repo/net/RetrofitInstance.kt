package com.team.seven.gocomix.repo.net


import com.team.seven.gocomix.model.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val comixApi: ComixApi by lazy {
        retrofit.create(ComixApi::class.java)
    }

}
