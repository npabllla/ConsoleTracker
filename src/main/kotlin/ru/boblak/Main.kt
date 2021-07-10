package ru.boblak

import ru.boblak.actions.*
import ru.boblak.api.Input
import ru.boblak.api.Output
import ru.boblak.api.UserAction
import ru.boblak.inputs.ConsoleInput
import ru.boblak.inputs.ValidateInput
import ru.boblak.outputs.ConsoleOutput
import ru.boblak.repositories.MemTracker
import ru.boblak.repositories.SqlTracker

fun main() {
    val output: Output = ConsoleOutput()
    val input: Input = ValidateInput(output, ConsoleInput())
    val tracker = SqlTracker()
    val actions = ArrayList<UserAction>()
    actions.add(CreateAction(output))
    actions.add(DeleteAction(output))
    actions.add(EditAction(output))
    actions.add(FindIdAction(output))
    actions.add(FindNameAction(output))
    actions.add(ShowAction(output))
    actions.add(ExitAction())
    StartUI(output).init(input, tracker, actions)
}