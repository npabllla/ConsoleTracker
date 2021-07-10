package ru.boblak.outputs

import ru.boblak.api.Output

class ConsoleOutput: Output {
    override fun println(obj: Any) {
        kotlin.io.println(obj)
    }
}