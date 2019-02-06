package com.miho.sqldslexample.v1

fun main() {
    Class.forName("com.mysql.jdbc.Driver")

    val sql = sql(
        "jdbc:mysql://localhost:3316/dsl_test?useSSL=false&requireSSL=false&autoReconnect=true&timezone=Europe/Vienna",
        "root",
        "root"
    )

    val result = sql select "*" from "test_table" where "location = 'Salzburg'"

    println(result)
}