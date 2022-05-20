package com.team.seven.gocomix.domaim.service

import com.team.seven.gocomix.domaim.Result

interface TranslateService {

    suspend fun translateIntoRussian(textBlock: String): Result<String>
}
