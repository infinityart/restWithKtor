package com.restWithKtor

import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import java.util.*
import kotlin.collections.ArrayList

class UserController {

    fun getAll(): ArrayList<User> {
        val users: ArrayList<User> = arrayListOf()

        transaction {
            UserDAO.selectAll().map {
                users.add(
                    User(
                        id = it[UserDAO.id],
                        firstName = it[UserDAO.firstName],
                        lastName = it[UserDAO.lastName],
                        age = it[UserDAO.age]
                    )
                )
            }
        }
        return users
    }

    fun insert(user: UserDTO) {
        transaction {
            UserDAO.insert {
                it[id] = UUID.randomUUID()
                it[firstName] = user.firstName
                it[lastName] = user.lastName
                it[age] = user.age
            }
        }
    }

    fun update(user: UserDTO, id: UUID) {
        transaction {
            UserDAO.update({ UserDAO.id eq id }) {
                it[age] = user.age
                it[firstName] = user.firstName
                it[lastName] = user.lastName
            }
        }
    }

    fun delete(id: UUID) {
        transaction {
            UserDAO.deleteWhere { UserDAO.id eq id }
        }
    }
}