package com.team.seven.gocomix.repo

import com.team.seven.gocomix.model.Author
import com.team.seven.gocomix.model.Comix
import com.team.seven.gocomix.model.Page

class ComixRepositoryFakeImpl : ComixRepository {

    override fun getComics(): List<Comix> {
        return listOf(
            Comix(
                id = 1,
                title = "Звездные войны: часть 4",
                description = "Погрузитесь в мир звездных войн после переломного момента " +
                    "гражданской войны: уничтожения величайшего оружия галактической империи",
                cover = "https://drive.google.com/uc?export=view" +
                    "&id=1bdWCGz5enXt11ECg0sKeDZJPn84Z8lxW",
                rating = 5.0,
                author = Author(
                    id = 1,
                    login = "Some Author",
                    email = "example@google.com"
                )
            )
        )
    }

    override fun getPages(comixId: Int): List<Page> {
        return listOf(
            Page(
                id = 1,
                comixId = 1,
                number = 1,
                image = "https://drive.google.com/uc?export=view" +
                    "&id=11AYDbkIma6j459aXOa7JqdWGSDGtaoAA"
            ),
            Page(
                id = 2,
                comixId = 1,
                number = 2,
                image = "https://drive.google.com/uc?export=view" +
                    "&id=1v2u5i0CPjuy1LN9yqVg1gQ-3tswJ733X"
            ),
            Page(
                id = 3,
                comixId = 1,
                number = 3,
                image = "https://drive.google.com/uc?export=view" +
                    "&id=1MVqRsG66jIEgVhbpASsDu6xhto7TE9tU"
            )
        )
    }
}
