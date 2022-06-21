package com.coursework.plugins

import com.coursework.features.LoginInputRemoteModel
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.server.util.*

@kotlinx.serialization.Serializable
data class Obj(
    val name: String
)

fun Application.configureRouting() {
    routing{
        static("/") {
            resources("web")

            }
        }
    }
