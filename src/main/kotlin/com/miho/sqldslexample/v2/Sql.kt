package com.miho.sqldslexample.v2

import java.sql.DriverManager

class Sql(private val url: String, private val user: String, private val password: String) {

    infix fun select(query: String) = executeQuery("SELECT $query")

    private fun executeQuery(query: String): List<Map<String, Any>> =
        DriverManager.getConnection(url, user, password)
            .use { connection ->
                connection.createStatement().use { stmt ->
                    stmt.executeQuery(query).use { rs ->
                        val metaData = rs.metaData
                        val colCount = metaData.columnCount

                        val result = mutableListOf<Map<String, Any>>()

                        while (rs.next()) {
                            val map = mutableMapOf<String, Any>()
                            for (i in 1..colCount)
                                map[metaData.getColumnName(i)] = rs.getObject(i)

                            result.add(map)
                        }

                        result
                    }
                }
            }

}