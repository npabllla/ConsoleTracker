package ru.boblak

import ru.boblak.api.Input
import ru.boblak.api.Output
import ru.boblak.api.Store
import ru.boblak.api.UserAction
import ru.boblak.repositories.MemTracker

data class StartUI(val out: Output) {
    fun init(input: Input, tracker: Store, actions: List<UserAction>) {
        var run = true
        while (run) {
            this.showMenu(actions)
            val select = input.askInt("Select: ")
            if (select < 0 || select >= actions.size) {
                out.println("Wrong input, you can select: 0 .. " + (actions.size - 1))
                continue
            }
            val action = actions[select]
            run = action.execute(input, tracker)
        }
    }

    private fun showMenu(actions: List<UserAction>) {
        out.println("Menu.")
        for (index in actions.indices) {
            out.println(index.toString() + ". " + actions[index].name())
        }
    }
}