package com.team.seven.gocomix.di

import com.team.seven.gocomix.repo.ComixRepository
import com.team.seven.gocomix.repo.net.ComixNetRepository
import com.team.seven.gocomix.repo.net.ComixService
import com.team.seven.gocomix.data.net.ImageService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val COMICS_SERVER_URL = "http://185.46.10.196:8080"

    @Singleton
    @Provides
    fun provideComicRepository(comixService: ComixService): ComixRepository {
        return ComixNetRepository(comixService)
    }

    @Singleton
    @Provides
    fun provideComixService(retrofit: Retrofit): ComixService {
        return retrofit.create(ComixService::class.java)
    }

    @Singleton
    @Provides
    fun provideImageService(retrofit: Retrofit): ImageService {
        return retrofit.create(ImageService::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(COMICS_SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
