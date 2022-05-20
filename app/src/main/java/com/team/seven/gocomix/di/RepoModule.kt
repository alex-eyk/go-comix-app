package com.team.seven.gocomix.di

import com.team.seven.gocomix.data.net.ComicService
import com.team.seven.gocomix.data.net.ImageService
import com.team.seven.gocomix.data.repo.comic.impl.ComicRemoteRepository
import com.team.seven.gocomix.data.repo.image.ImageLocalRepository
import com.team.seven.gocomix.data.repo.image.ImageRepository
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

    @Singleton
    @Provides
    fun provideComicRemoteRepository(
        comicService: ComicService
    ): ComicRemoteRepository {
        return ComicRemoteRepository(comicService)
    }

    @Singleton
    @Provides
    fun provideImageRemoteRepository(
        imageService: ImageService
    ): ImageRepository {
        return ImageRemoteRepository(imageService)
    }

    @Singleton
    @Provides
    fun provideImageLocalRepository(): ImageLocalRepository {
        return ImageLocalRepositoryImpl()
    }

    @Singleton
    @Provides
    fun provideRemoteImageRepository(imageService: ImageService): ImageRemoteRepository {
        return ImageRemoteRepository(imageService)
    }
}
