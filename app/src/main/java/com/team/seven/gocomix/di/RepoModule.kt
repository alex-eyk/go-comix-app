package com.team.seven.gocomix.di

import com.team.seven.gocomix.data.net.ImageService
import com.team.seven.gocomix.data.repo.image.impl.ImageLocalRepositoryImpl
import com.team.seven.gocomix.data.repo.image.impl.ImageRemoteRepository
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
    fun provideLocalImageRepository(): ImageLocalRepositoryImpl {
        return ImageLocalRepositoryImpl()
    }

    @Singleton
    @Provides
    fun provideRemoteImageRepository(imageService: ImageService): ImageRemoteRepository {
        return ImageRemoteRepository(imageService)
    }
}
