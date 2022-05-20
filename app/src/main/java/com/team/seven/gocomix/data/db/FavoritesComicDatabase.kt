@file:SuppressWarnings("UnnecessaryAbstractClass")

package com.team.seven.gocomix.data.db

import androidx.room.Database
import com.team.seven.gocomix.data.dao.FavoritesComicDao
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
abstract class FavoritesComicDatabase {

    abstract fun favoritesComicDao(): FavoritesComicDao

    abstract fun pagesDao(): PagesDao
}
