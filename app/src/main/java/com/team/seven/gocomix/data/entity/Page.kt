package com.team.seven.gocomix.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Page(

    @SerializedName("id")
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @SerializedName("comix_id")
    @ColumnInfo(name = "comic_id")
    val comicId: Int,

    @SerializedName("image_id")
    @ColumnInfo(name = "image_id")
    val imageId: Int,

    @SerializedName("number")
    @ColumnInfo(name = "number")
    val number: Int
)
