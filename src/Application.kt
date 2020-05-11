package com.restWithKtor

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.http.*
import com.fasterxml.jackson.databind.*
import io.ktor.jackson.*
import io.ktor.features.*
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import java.util.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    fun initDB() {
        val config = HikariConfig("/hikari.properties")
        config.schema = "restwithktor"

        val ds = HikariDataSource(config)

        Database.connect(ds)
    }

    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    initDB()

    routing {

        val userController = UserController()

        get("/users") {
            call.respond(userController.getAll())
        }

        post("/user") {
            val userDTO = call.receive<UserDTO>()

            userController.insert(userDTO)
            call.respond(HttpStatusCode.Created)
        }

        put("/user/{id}") {
            val id: UUID = UUID.fromString(call.parameters["id"])
            val userDTO = call.receive<UserDTO>()

            userController.update(userDTO, id)
            call.respond(HttpStatusCode.OK)
        }

        delete("/user/{id}") {
            val id: UUID = UUID.fromString(call.parameters["id"])

            userController.delete(id)
            call.respond(HttpStatusCode.OK)
        }
    }
}

