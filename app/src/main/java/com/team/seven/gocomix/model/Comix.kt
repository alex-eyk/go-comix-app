package com.team.seven.gocomix.model

data class Comix(
    val author: Author,
    @Deprecated("use `Comix.coverId` and method `image/{id}`")
    val cover: Image,
    val coverId: Int = 1,
    val description: String,
    val id: Int,
    val rating: Float,
    val title: String
)
