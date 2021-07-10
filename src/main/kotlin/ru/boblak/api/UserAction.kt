package ru.boblak.api

import ru.boblak.actions.*

interface UserAction {
    fun name(): String

    fun execute(input: Input, tracker: Store): Boolean
}