package ru.boblak.inputs

import ru.boblak.api.Input

class ConsoleInput: Input{
    override fun askString(question: String): String {
        println(question)
        return readLine()!!
    }

    override fun askInt(question: String): Int {
        return Integer.valueOf(askString(question))
    }
}