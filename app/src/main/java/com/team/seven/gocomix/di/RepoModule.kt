package com.team.seven.gocomix.di

import com.team.seven.gocomix.data.net.ImageService
import com.team.seven.gocomix.data.repo.impl.LocalImageRepository
import com.team.seven.gocomix.data.repo.impl.RemoteImageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    private const val COMICS_SERVER_URL = "http://37.140.199.63:8080"

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
