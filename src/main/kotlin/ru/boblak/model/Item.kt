package ru.boblak.model

import java.time.LocalDate

data class Item(
    var id: Int = -1,
    var name: String = "",
    val created: LocalDate = LocalDate.now()
) : Comparable<Item> {

    override fun compareTo(other: Item): Int {
        return id.compareTo(other.id)
    }

}