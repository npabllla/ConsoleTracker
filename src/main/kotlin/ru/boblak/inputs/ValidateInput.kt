package ru.boblak.inputs

import ru.boblak.api.Input
import ru.boblak.api.Output

data class ValidateInput(val out: Output, val inp: Input): Input {
    override fun askString(question: String): String {
        return inp.askString(question)
    }

    override fun askInt(question: String): Int {
        var invalid = true
        var value = -1
        do {
            try {
                value = inp.askInt(question)
                invalid = false
            } catch (nfe: NumberFormatException) {
                out.println("Please enter validate data again.")
            }
        } while (invalid)
        return value
    }
}