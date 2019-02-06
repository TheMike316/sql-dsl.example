package com.miho.sqldslexample.v2

inline fun sql(block: SqlBuilder.() -> Unit) = SqlBuilder().apply(block).build()