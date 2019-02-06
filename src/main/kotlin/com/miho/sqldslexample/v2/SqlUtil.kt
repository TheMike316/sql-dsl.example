package com.miho.sqldslexample.v2

fun sql(block: SqlBuilder.() -> Unit) = SqlBuilder().apply(block).build()