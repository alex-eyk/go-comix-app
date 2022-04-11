package com.team.seven.gocomix.repo

import com.team.seven.gocomix.model.Comix
import com.team.seven.gocomix.model.Page

interface ComixRepository {

    fun getComics(): List<Comix>

    fun getPages(comixId: Int): List<Page>
}
