package ru.boblak.actions

import ru.boblak.api.Input
import ru.boblak.api.Output
import ru.boblak.api.Store
import ru.boblak.api.UserAction

data class ShowAction(val out: Output): UserAction {
    override fun name(): String {
        return "=== All items ==="
    }

    override fun execute(input: Input, tracker: Store): Boolean {
        for (item in tracker.findAll()) {
            println(item)
        }
        return true
    }
}