package com.team.seven.gocomix.di

import android.content.Context
import androidx.room.Room
import com.team.seven.gocomix.data.db.FavouriteComicsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideFavouriteComicsDao(db: FavouriteComicsDatabase) = db.favoritesComicDao()

    @Singleton
    @Provides
    fun providePagesDao(db: FavouriteComicsDatabase) = db.pagesDao()

    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext appContext: Context
    ): FavouriteComicsDatabase {
        return Room.databaseBuilder(
            appContext,
            FavouriteComicsDatabase::class.java,
            "FavoritesComics"
        ).build()
    }
}
