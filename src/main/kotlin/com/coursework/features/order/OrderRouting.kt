package com.coursework.features.order

import com.coursework.cache.InMemoryCache
import com.coursework.cache.TokenCache
import com.coursework.features.LoginInputRemoteModel
import com.coursework.features.LoginOutputRemoteModel
import com.coursework.features.login.LoginController
import com.coursework.features.users.UsersController
import com.coursework.plugins.Obj
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.UUID

fun Application.configureOrderRouting() {

    routing {
        post ("/addOrder") {
            val orderController = OrderController(call)
            orderController.addOrder()
        }
        get("/orders/fetchAll") {
            val orderController = OrderController(call)
            orderController.fetchAllOrders()
        }
        post("/orders/delete") {
            val orderController = OrderController(call)
            orderController.deleteOrder()
        }
    }
}