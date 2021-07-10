package ru.boblak.repositories

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import ru.boblak.model.Item

internal class MemTrackerTest {
    private val tracker: MemTracker = MemTracker()

    @Test
    fun add() {
        tracker.add(Item(name = "Test"))
        assertEquals(
            Item(1, "Test"),
            tracker.findAll().first()
        )
    }

    @Test
    fun findAll() {
        tracker.add(Item(name = "Test"))
        assertEquals(
            listOf(Item(1, "Test")),
            tracker.findAll()
        )
    }

    @Test
    fun findByName() {
        tracker.add(Item(name = "Test"))
        assertEquals(
            listOf(Item(1, "Test")),
            tracker.findByName("Test")
        )
    }

    @Test
    fun findById() {
        tracker.add(Item(name = "Test"))
        assertEquals(
            Item(1, "Test"),
            tracker.findById(1)
        )
    }

    @Test
    fun replace() {
        tracker.add(Item(name = "Test"))
        assertTrue(
            tracker.replace(1, Item(name = "NewTest"))
        )
        assertEquals(
            Item(1, "NewTest"),
            tracker.findById(1)
        )
    }

    @Test
    fun delete() {
        tracker.add(Item(name = "Test"))
        assertTrue(
            tracker.delete(1)
        )
        assertTrue(
            tracker.findAll().isEmpty()
        )
    }
}