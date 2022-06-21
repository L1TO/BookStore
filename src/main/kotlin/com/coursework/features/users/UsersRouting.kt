package com.coursework.features.users

import io.ktor.server.application.*
import io.ktor.server.routing.*


fun Application.configureUsersRouting() {

    routing {
        get("/users/fetchAll") {
            val usersController = UsersController(call)
            usersController.fetchAllUsers()
        }
        post ( "/users/delete"  ) {
            val usersController = UsersController(call)
            usersController.deleteUser()
        }
        post ( "/users/fetch"  ) {
            val usersController = UsersController(call)
            usersController.fetchUser()
        }
        post ("/users/update") {
            val usersController = UsersController(call)
            usersController.updateUser()
        }
    }
}