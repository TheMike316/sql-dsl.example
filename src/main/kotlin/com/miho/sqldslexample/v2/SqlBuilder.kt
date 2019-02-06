package com.miho.sqldslexample.v2

class SqlBuilder {

    var url: String = ""

    var user: String = ""

    var password: String = ""

    fun build() = Sql(url, user, password)
}