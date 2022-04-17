package com.team.seven.gocomix.model

data class Comix(
    val author: Author,
    val cover: Image,
    val description: String,
    val id: Int,
    val rating: Double,
    val title: String
)
