package com.team.seven.gocomix.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.team.seven.gocomix.data.entity.Comic

@Dao
interface FavoritesComicDao {

    @Query("SELECT * FROM `comic`")
    suspend fun getAll(): List<Comic>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(comic: Comic)

    @Delete
    suspend fun delete(comic: Comic)
}
