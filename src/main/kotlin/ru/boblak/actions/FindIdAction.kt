package ru.boblak.actions

import ru.boblak.api.Input
import ru.boblak.api.Output
import ru.boblak.api.Store
import ru.boblak.api.UserAction

data class FindIdAction(val out: Output): UserAction {
    override fun name(): String {
        return "=== Find item by id ==="
    }

    override fun execute(input: Input, tracker: Store): Boolean {
        val itemId = input.askInt("Enter item id: ")
        val item = tracker.findById(itemId)
        if (item != null) {
            out.println(item)
        } else {
            out.println("Item not found - id is incorrect")
        }
        return true
    }
}