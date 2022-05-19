package com.team.seven.gocomix.di

import com.team.seven.gocomix.repo.image.LocalImageRepository
import com.team.seven.gocomix.repo.image.RemoteImageRepository
import com.team.seven.gocomix.repo.net.ImageService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    private const val COMICS_SERVER_URL = "http://185.46.10.196:8080"

    @Singleton
    @Provides
    fun provideLocalImageRepository(): LocalImageRepository {
        return LocalImageRepository()
    }

    @Singleton
    @Provides
    fun provideRemoteImageRepository(imageService: ImageService): RemoteImageRepository {
        return RemoteImageRepository(imageService)
    }
}
