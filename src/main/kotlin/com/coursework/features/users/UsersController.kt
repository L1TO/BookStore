package com.coursework.features.users

import com.coursework.database.users.UserUpdateDTO
import com.coursework.database.users.Users
import com.coursework.features.LoginInputRemoteModel
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class UsersController(private val call: ApplicationCall) {

    suspend fun fetchAllUsers() {
        call.respond(Users.fetchAllUsers())
    }

    suspend fun fetchUser() {
        val receive = call.receive<UserLoginRemoteModel>()
        val userDTO = Users.fetchUser(receive.login)

        if (userDTO == null) {
            call.respond(HttpStatusCode.BadRequest, "User not found")
        } else {
            call.respond(userDTO)
        }
    }

        suspend fun updateUser() {
            val receive = call.receive<UserUpdateRemoteModel>()
            val userDTO = Users.fetchUser(receive.login)
            if (userDTO != null){
                val userUpdateDTO = Users.update(receive)
                call.respond(userDTO)
            } else{
                call.respond(HttpStatusCode.BadRequest)
            }
        }



        suspend fun deleteUser() {
            val receive = call.receive<UserLoginRemoteModel>()
            val userDTO = Users.fetchUser(receive.login)
            if (userDTO != null) {
                Users.delete(receive.login)
                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.BadRequest)
            }
        }
}