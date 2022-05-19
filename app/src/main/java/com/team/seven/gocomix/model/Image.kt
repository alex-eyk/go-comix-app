package com.team.seven.gocomix.model

@Deprecated("use API method image/{id} instead")
data class Image(
    val id: Long,
    val origin: String,
    val small: String,
    val loading: String
)
