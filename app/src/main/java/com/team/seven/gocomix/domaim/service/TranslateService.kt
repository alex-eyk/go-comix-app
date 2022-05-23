package com.team.seven.gocomix.domaim.service

import com.team.seven.gocomix.util.Either

interface TranslateService {

    suspend fun translateIntoRussian(text: String): Either<String>
}
