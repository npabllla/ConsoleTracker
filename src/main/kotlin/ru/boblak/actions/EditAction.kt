package ru.boblak.actions

import ru.boblak.api.Input
import ru.boblak.api.Output
import ru.boblak.api.Store
import ru.boblak.api.UserAction
import ru.boblak.model.Item

data class EditAction(val out: Output): UserAction {
    override fun name(): String {
        return "=== Edit item ==="
    }

    override fun execute(input: Input, tracker: Store): Boolean {
        val itemId = input.askInt("Enter item id: ")
        val newName = input.askString("Enter new item name: ")
        val newItem = Item(itemId, newName)
        if (tracker.replace(itemId, newItem)) {
            out.println("Replace completed successful")
        } else {
            out.println("Replace wasn't complete - item wasn't found")
        }
        return true
    }
}