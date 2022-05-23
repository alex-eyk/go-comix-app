package com.team.seven.gocomix.di

import com.team.seven.gocomix.data.dao.FavouriteComicsDao
import com.team.seven.gocomix.data.net.ComicService
import com.team.seven.gocomix.data.net.ImageService
import com.team.seven.gocomix.data.repo.comic.ComicsRepository
import com.team.seven.gocomix.data.repo.comic.FavouriteComicsRepository
import com.team.seven.gocomix.data.repo.comic.impl.ComicsRemoteRepository
import com.team.seven.gocomix.data.repo.comic.impl.FavouriteComicsLocalRepository
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
    fun provideFavouriteComicsRepository(
        favouriteComicsDao: FavouriteComicsDao
    ): FavouriteComicsRepository {
        return FavouriteComicsLocalRepository(favouriteComicsDao)
    }

    @Singleton
    @Provides
    fun provideComicRemoteRepository(
        comicService: ComicService
    ): ComicsRepository {
        return ComicsRemoteRepository(comicService)
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
