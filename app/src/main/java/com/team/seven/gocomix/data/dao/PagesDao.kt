package com.team.seven.gocomix.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.team.seven.gocomix.data.entity.Page

@Dao
interface PagesDao {

    @Query("SELECT * FROM `page` WHERE `comic_id` == :comicId")
    suspend fun getAllByComicId(comicId: Int): List<Page>

    @Insert
    suspend fun insert(vararg page: Page)

    @Query("DELETE FROM `page` WHERE `comic_id` == :comicId")
    suspend fun deleteAllByComicId(comicId: Int)
}
