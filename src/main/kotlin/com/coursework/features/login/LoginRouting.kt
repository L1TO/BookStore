package com.coursework.features.login

import com.coursework.cache.InMemoryCache
import com.coursework.cache.TokenCache
import com.coursework.features.LoginInputRemoteModel
import com.coursework.features.LoginOutputRemoteModel
import com.coursework.plugins.Obj
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.UUID

fun Application.configureLoginRouting() {

    routing {
        post("/login") {
            val loginController = LoginController(call)
            loginController.performLogin()
        }
        post ("/token") {
            val loginController = LoginController(call)
            loginController.fetchToken()
        }
    }
}