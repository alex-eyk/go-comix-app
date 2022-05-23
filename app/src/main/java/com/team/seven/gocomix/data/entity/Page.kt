package com.team.seven.gocomix.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Page(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "comic_id")
    val comicId: Int,

    @ColumnInfo(name = "image_id")
    val imageId: Int,

    @ColumnInfo(name = "number")
    val number: Int
)
