package com.team.seven.gocomix.model

@Deprecated(
    message = "Use new Author object without link to image",
    ReplaceWith("com.team.seven.gocomix.data.entity.Author")
)
data class Author(
    val id: Int,
    val photo: Image?,
    val email: String,
    val login: String
)
