package ru.boblak.api

import ru.boblak.model.Item

interface Store {
    fun add(item: Item): Item

    fun findAll(): MutableList<Item>

    fun findByName(name: String): MutableList<Item>

    fun findById(id: Int): Item

    fun replace(id: Int, item: Item): Boolean

    fun delete(id: Int): Boolean
}