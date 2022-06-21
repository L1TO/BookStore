package com.coursework.features.registration

import com.coursework.cache.InMemoryCache
import com.coursework.cache.TokenCache
import com.coursework.database.tokens.TokenDTO
import com.coursework.database.tokens.Tokens
import com.coursework.database.users.UserDTO
import com.coursework.database.users.Users
import com.coursework.utils.isValidEmail
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.jetbrains.exposed.exceptions.ExposedSQLException
import java.util.*

class RegisterController(private val call: ApplicationCall) {

    suspend fun registerNewUser() {
        val registerReceiveRemote = call.receive<RegisterInputRemoteModel>()
        if (!registerReceiveRemote.email.isValidEmail()) {
            call.respond(HttpStatusCode.BadRequest, "Email is not valid")
        }

        val userDTO = Users.fetchUser(registerReceiveRemote.login)
        if (userDTO != null) {
            call.respond(HttpStatusCode.Conflict, "User already exists")
        } else {
            val token = UUID.randomUUID().toString()

            try {
                Users.insert(
                    UserDTO(
                        login = registerReceiveRemote.login,
                        password = registerReceiveRemote.password,
                        email = registerReceiveRemote.email,
                        username = registerReceiveRemote.username
                    )
                )
            } catch (e: ExposedSQLException) {
                call.respond(HttpStatusCode.Conflict, "User already exists")
            } catch (e: Exception) {
                call.respond(HttpStatusCode.BadRequest, "Can't create user ${e.localizedMessage}")
            }

            Tokens.insert(
                TokenDTO(
                    id = UUID.randomUUID().toString(),
                    login = registerReceiveRemote.login,
                    token = token
                )
            )

            call.respond(RegisterOutputRemoteModel(token = token))
        }
    }
}