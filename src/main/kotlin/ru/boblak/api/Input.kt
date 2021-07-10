package ru.boblak.api

interface Input {
    fun askString(question: String): String

    fun askInt(question: String): Int
}