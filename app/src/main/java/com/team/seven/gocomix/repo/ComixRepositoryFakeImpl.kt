@file:Suppress("SpellCheckingInspection", "MagicNumber")

package com.team.seven.gocomix.repo

import com.team.seven.gocomix.model.Author
import com.team.seven.gocomix.model.Comix
import com.team.seven.gocomix.model.Image
import com.team.seven.gocomix.model.Page
import kotlinx.coroutines.delay

class ComixRepositoryFakeImpl : ComixRepository {

    override suspend fun getComics(): Result<List<Comix>> {
        delay((500 + Math.random() * 1000).toLong())
        return Result.success(
            listOf(
                Comix(
                    id = 1,
                    title = "Звездные войны: часть 4",
                    description = "Погрузитесь в мир звездных войн после переломного момента " +
                        "гражданской войны: уничтожения величайшего оружия галактической империи",
                    cover = Image(
                        id = 1,
                        origin = "https://drive.google.com/uc?export=view" +
                            "&id=1bdWCGz5enXt11ECg0sKeDZJPn84Z8lxW",
                        small = "https://drive.google.com/uc?export=view" +
                            "&id=1joUPpAUZ4z-1MyqUJDD_Kp9sauJKPLHt",
                        loading = "https://drive.google.com/uc?export=view" +
                            "&id=1a2EcpaofMpDLu2POzW22J-4w9qjDxgxz"
                    ),
                    rating = 5.0F,
                    author = Author(
                        id = 1,
                        photo = null,
                        login = "Some Author",
                        email = "example@google.com"
                    )
                ),
                Comix(
                    id = 2,
                    title = "Звездные войны: часть 8",
                    description = "Погрузитесь в мир звездных войн после переломного момента " +
                        "гражданской войны: уничтожения величайшего оружия галактической империи",
                    cover = Image(
                        id = 2,
                        origin = "https://drive.google.com/uc?export=view&id=" +
                            "1WIefgzJ49keMfvjutH3rtjlIYmxOEnyM",
                        small = "https://drive.google.com/uc?export=view&id=" +
                            "1WpEviCVSlo7F-mrJGt7VLnJMCRyH0-5S",
                        loading = "https://drive.google.com/uc?export=view&id=" +
                            "1Z-FAnX4o3bz2Dn5YZQv5B-GZ3ESKIiXu"
                    ),
                    rating = 4.5F,
                    author = Author(
                        id = 1,
                        photo = null,
                        login = "Some Author",
                        email = "example@google.com"
                    )
                )
            )
        )
    }

    override suspend fun getPages(comixId: Int): Result<List<Page>> {
        return Result.success(
            listOf(
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
        )
    }
}
