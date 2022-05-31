@file:SuppressWarnings("UnnecessaryAbstractClass")

package com.team.seven.gocomix.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.team.seven.gocomix.data.dao.FavouriteComicsDao
import com.team.seven.gocomix.data.dao.PagesDao
import com.team.seven.gocomix.data.entity.Comic
import com.team.seven.gocomix.data.entity.Page

@Database(
    entities = [
        Comic::class,
        Page::class
    ],
    version = 1
)
abstract class FavouriteComicsDatabase : RoomDatabase() {

    abstract fun favoritesComicDao(): FavouriteComicsDao

    abstract fun pagesDao(): PagesDao
}
