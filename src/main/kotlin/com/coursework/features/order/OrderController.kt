package com.coursework.features.order

import com.coursework.cache.InMemoryCache
import com.coursework.cache.TokenCache
import com.coursework.database.orders.OrderDTO
import com.coursework.database.orders.Orders
import com.coursework.database.tokens.TokenDTO
import com.coursework.database.tokens.Tokens
import com.coursework.database.users.UserDTO
import com.coursework.database.users.Users
import com.coursework.features.OrderIdModel
import com.coursework.features.OrderInputRemoteModel
import com.coursework.features.LoginInputRemoteModel
import com.coursework.features.LoginOutputRemoteModel
import com.coursework.features.users.UserLoginRemoteModel
import com.coursework.features.users.UserUpdateRemoteModel
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.jetbrains.exposed.exceptions.ExposedSQLException
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class OrderController(private val call: ApplicationCall) {


    suspend fun addOrder() {
        val receive = call.receive<OrderInputRemoteModel>()
        val orderDTO = Orders.fetchOrder(receive.id)

        if (orderDTO != null) {
            call.respond(HttpStatusCode.BadRequest, "Order already exists")
        } else {
            try {
                Orders.insert(
                    OrderDTO(
                        id = receive.id,
                        book_name = receive.book_name,
                        price = receive.price,
                        quantity = receive.quantity,
                        order_date = receive.order_date,
                        total = receive.total,
                        customer_name = receive.customer_name,
                        customer_address = receive.customer_address,
                        customer_email = receive.customer_email,
                    )
                )
                call.respond(receive.id)
            } catch (e: ExposedSQLException) {
                call.respond(HttpStatusCode.Conflict, "User already exists")
            }
        }
    }

    suspend fun fetchAllOrders() {
        call.respond(Orders.fetchAllOrders())
    }

    suspend fun deleteOrder() {
        val receive = call.receive<OrderIdModel>()
        val orderDTO = Orders.fetchOrder(receive.id)
        if (orderDTO != null) {
            Orders.delete(orderDTO.id)
            call.respond(HttpStatusCode.OK)
        } else {
            call.respond(HttpStatusCode.BadRequest)
        }
    }
}