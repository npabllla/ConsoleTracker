package ru.boblak.repositories

import ru.boblak.api.Store
import ru.boblak.model.Item

class MemTracker: Store {
    private val items = mutableListOf<Item>()
    private var ids: Int = 1

    override fun add(item: Item): Item {
        item.id = ids++
        items.add(item)
        return item
    }

    override fun findAll(): MutableList<Item> {
        return items
    }

    override fun findByName(name: String): MutableList<Item> {
        val result = mutableListOf<Item>()
        for (item in items) {
            if (item.name == name) {
                result.add(item)
            }
        }
        return result
    }

    override fun findById(id: Int): Item {
        val index = indexOf(id)
        return items[index]
    }

    override fun replace(id: Int, item: Item): Boolean {
        var res = false
        val index = indexOf(id)
        if (index != -1) {
            item.id = id
            items[index] = item
            res = true
        }
        return res
    }

    override fun delete(id: Int): Boolean {
        var res = false
        val index = indexOf(id)
        if (index != -1) {
            items.removeAt(index)
            res = true
        }
        return res
    }

    private fun indexOf(id: Int): Int {
        var rsl: Int = -1
        for (i in items.indices) {
            if (items[i].id == id) {
                rsl = i
                break
            }
        }
        return rsl
    }
}