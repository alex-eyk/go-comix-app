package com.team.seven.gocomix.data.entity

import androidx.room.ColumnInfo

data class Author(

    @ColumnInfo(name = "author_id")
    val id: Int,

    @ColumnInfo(name = "author_photo_id")
    val photoId: Int,

    @ColumnInfo(name = "author_email")
    val email: String,

    @ColumnInfo(name = "author_login")
    val login: String
)
