package com.team.seven.gocomix.model

@Deprecated(
    message = "Use new Page object without link to image",
    ReplaceWith("com.team.seven.gocomix.data.entity.Page")
)
data class Page(
    val comixId: Int,
    val id: Int,
    val image: String,
    val number: Int
)
