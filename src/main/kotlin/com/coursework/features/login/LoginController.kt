package com.coursework.features.login

import com.coursework.cache.InMemoryCache
import com.coursework.cache.TokenCache
import com.coursework.database.tokens.TokenDTO
import com.coursework.database.tokens.Tokens
import com.coursework.database.users.Users
import com.coursework.features.LoginInputRemoteModel
import com.coursework.features.LoginOutputRemoteModel
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import java.util.*

class LoginController(private val call: ApplicationCall) {

    suspend fun performLogin() {
        val receive = call.receive<LoginInputRemoteModel>()
        val userDTO = Users.fetchUser(receive.login)

        if (userDTO == null) {
            call.respond(HttpStatusCode.BadRequest, "User not found")
        } else {
            if (userDTO.password == receive.password) {
                val token = UUID.randomUUID().toString()
                Tokens.insert(
                    TokenDTO(
                        id = UUID.randomUUID().toString(), login = receive.login,
                        token = token
                    )
                )

                call.respond(LoginOutputRemoteModel(token = token))
            } else {
                call.respond(HttpStatusCode.BadRequest, "Invalid password")
            }
        }
    }

    suspend fun fetchToken() {
        val receive = call.receive<LoginOutputRemoteModel>()
        val tokenDTO = Tokens.fetchToken(receive.token)
        if (tokenDTO != null) {
            if (receive.token != tokenDTO.token){
                call.respond(HttpStatusCode.BadRequest, "Token does not exist")
            } else{
                call.respond(LoginOutputRemoteModel(token = tokenDTO.token))
            }
        } else {
            call.respond(HttpStatusCode.BadRequest, "Token does not exist")
        }
    }
}