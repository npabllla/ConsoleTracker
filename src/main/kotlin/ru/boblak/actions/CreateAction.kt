package ru.boblak.actions

import ru.boblak.api.Input
import ru.boblak.api.Output
import ru.boblak.api.Store
import ru.boblak.api.UserAction
import ru.boblak.model.Item

data class CreateAction(val out: Output): UserAction {
    override fun name(): String {
        return "=== Create new item ==="
    }

    override fun execute(input: Input, tracker: Store): Boolean {
        tracker.add(Item(name = input.askString("Enter name: ")))
        return true
    }
}