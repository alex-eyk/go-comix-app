package com.team.seven.gocomix.model

@Deprecated(
    message = "Use new Comic object with correct name and without link to image",
    ReplaceWith("com.team.seven.gocomix.data.entity.Comic")
)
data class Comix(
    val id: Int,
    val title: String,
    val description: String,
    val rating: Float,
    val coverId: Int = 1,
    val author: Author,

    @Deprecated("Use `Comix.coverId` and method `image/{id}`")
    val cover: Image

)
