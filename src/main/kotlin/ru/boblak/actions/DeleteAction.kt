package ru.boblak.actions

import ru.boblak.api.Input
import ru.boblak.api.Output
import ru.boblak.api.Store
import ru.boblak.api.UserAction

data class DeleteAction(val out: Output): UserAction {
    override fun name(): String {
        return "=== Delete item ==="
    }

    override fun execute(input: Input, tracker: Store): Boolean {
        val itemId = input.askInt("Enter id of item: ")
        if (tracker.delete(itemId)) {
            out.println("Deleting completed successful")
        } else {
            out.println("Item was not found")
        }
        return true
    }
}