package ru.boblak.actions

import ru.boblak.api.Input
import ru.boblak.api.Store
import ru.boblak.api.UserAction

class ExitAction: UserAction {
    override fun name(): String {
        return "=== Exit ==="
    }

    override fun execute(input: Input, tracker: Store): Boolean {
        return false
    }
}