package com.team.seven.gocomix.domaim.service

import com.team.seven.gocomix.domaim.Result

interface TranslateService {

    suspend fun translateIntoRussian(textBlocks: List<String>): Result<List<String>>
}
