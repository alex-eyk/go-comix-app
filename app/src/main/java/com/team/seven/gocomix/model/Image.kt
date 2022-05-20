package com.team.seven.gocomix.model

@Deprecated("Use API method image/{id} with quality parameter instead")
data class Image(
    val id: Long,
    val origin: String,
    val small: String,
    val loading: String
)
