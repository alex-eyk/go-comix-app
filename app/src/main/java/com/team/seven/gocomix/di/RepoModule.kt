package com.team.seven.gocomix.di

import com.team.seven.gocomix.data.dao.FavouriteComicsDao
import com.team.seven.gocomix.data.net.ComicService
import com.team.seven.gocomix.data.repo.comic.ComicsRepository
import com.team.seven.gocomix.data.repo.comic.FavouriteComicsRepository
import com.team.seven.gocomix.data.repo.comic.impl.ComicsRemoteRepository
import com.team.seven.gocomix.data.repo.comic.impl.FavouriteComicsLocalRepository
import com.team.seven.gocomix.data.repo.image.ImageLocalRepository
import com.team.seven.gocomix.data.repo.image.impl.ImageLocalRepositoryImpl
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
    fun provideImageLocalRepository(): ImageLocalRepository {
        return ImageLocalRepositoryImpl()
    }

}
