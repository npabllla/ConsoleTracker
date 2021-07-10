package ru.boblak.repositories

import ru.boblak.api.Store
import ru.boblak.model.Item
import java.sql.*
import java.util.Properties


class SqlTracker : AutoCloseable, Store {
    var cn: Connection

    init {
        try {
            SqlTracker::class.java.classLoader.getResourceAsStream("app.properties").use { `in` ->
                val config = Properties()
                config.load(`in`)
                cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
                )
            }
        } catch (e: Exception) {
            throw IllegalStateException(e)
        }
    }

    override fun add(item: Item): Item {
        try {
            cn.prepareStatement(
                "INSERT INTO items(name, created) VALUES(?, ?)",
                Statement.RETURN_GENERATED_KEYS
            ).use { statement ->
                statement.setString(1, item.name)
                statement.setDate(2, Date.valueOf(item.created.toString()))
                val modifications = statement.executeUpdate()
                if (modifications < 1) {
                    throw  IllegalStateException("The Item already exist")
                }
                val keys = statement.generatedKeys
                if (keys.next()) {
                    item.id = keys.getInt(1)
                    return item
                }
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        throw IllegalStateException("Could not add the Item")
    }

    override fun findAll(): MutableList<Item> {
        return findByQuery(
            "SELECT * FROM items"
        )
    }

    override fun findByName(name: String): MutableList<Item> {
        return findByQuery(
            "SELECT * FROM items " +
                    "WHERE name = '$name'"
        )
    }

    override fun findById(id: Int): Item {
        return findByQuery(
            "SELECT * FROM items " +
                    "WHERE id = $id"
        ).first()
    }

    override fun replace(id: Int, item: Item): Boolean {
        return executeQuery(
            "UPDATE items " +
                    "SET name = ${item.name} " +
                    "WHERE id = $id"
        )
    }

    override fun delete(id: Int): Boolean {
        return executeQuery(
            "DELETE FROM items " +
                    "WHERE id = $id"
        )
    }

    private fun findByQuery(query: String): MutableList<Item> {
        val result = mutableListOf<Item>()
        try {
            cn.prepareStatement(query).use { statement ->
                statement.executeQuery().use { resultSet ->
                    while (resultSet.next()) {
                        result.add(
                            Item(
                                resultSet.getInt("id"),
                                resultSet.getString("name")
                            )
                        )
                    }
                }
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return result
    }

    private fun executeQuery(query: String): Boolean {
        var result = false
        try {
            cn.prepareStatement(query).use { statement ->
                result = statement.executeUpdate() > 0
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return result
    }

    override fun close() {
        cn.close()
    }
}