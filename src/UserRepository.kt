package com.restWithKtor

import org.jetbrains.exposed.sql.Table

object UserDAO : Table("user") {
    val id = uuid("id").primaryKey()
    val firstName = text("firstname")
    val lastName = text("lastname")
    val age = integer("age")
}