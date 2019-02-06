package com.miho.sqldslexample.v1

import java.sql.DriverManager

class SqlBuilder(private val url: String, private val user: String, private val password: String) {

    private var columns: List<String> = emptyList()

    private var table: String = ""

    private var whereClause: String = ""

    infix fun select(column: String): SqlBuilder {
        columns = listOf(column)

        return this
    }

    infix fun select(columns: List<String>): SqlBuilder {
        this.columns = columns

        return this
    }

    infix fun from(table: String): SqlBuilder {
        this.table = table

        return this
    }

    infix fun where(clause: String): List<Map<String, Any>> {
        whereClause = clause

        return executeQuery()
    }

    private fun buildQuery() = "SELECT ${columns.joinToString(",")} FROM $table WHERE $whereClause"

    private fun executeQuery(): List<Map<String, Any>> =
        DriverManager.getConnection(url, user, password)
            .use { connection ->
                connection.createStatement().use { stmt ->
                    stmt.executeQuery(buildQuery()).use { rs ->
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


fun sql(url: String, user: String, password: String) = SqlBuilder(url, user, password)