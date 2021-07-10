package ru.boblak.actions

import ru.boblak.api.Input
import ru.boblak.api.Output
import ru.boblak.api.Store
import ru.boblak.api.UserAction

data class FindNameAction(val out: Output): UserAction {
    override fun name(): String {
        return "=== Find item by name ==="
    }

    override fun execute(input: Input, tracker: Store): Boolean {
        val name = input.askString("Enter item name: ")
        val items = tracker.findByName(name)
        if (items.isNotEmpty()) {
            for (item in items) {
                out.println(item)
            }
        } else {
            out.println("Items not found - name is incorrect")
        }
        return true
    }
}