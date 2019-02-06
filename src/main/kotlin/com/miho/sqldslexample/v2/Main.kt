package com.miho.sqldslexample.v2

fun main() {
    Class.forName("com.mysql.jdbc.Driver")

    val result = sql {
        url =
            "jdbc:mysql://localhost:3316/dsl_test?useSSL=false&requireSSL=false&autoReconnect=true&timezone=Europe/Vienna"
        user = "root"
        password = "root"
    } select "* FROM test_table WHERE location = 'Salzburg'"

    println(result)
}