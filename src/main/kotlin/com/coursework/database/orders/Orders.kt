package com.coursework.database.orders

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

object Orders: Table("order"){
    private val id = Orders.integer("id")
    private val book_name = Orders.varchar("book_name", 25)
    private val quantity = Orders.integer("quantity")
    private val price = Orders.double("price")
    private val order_date = Orders.varchar("order_date", 25)
    private val total = Orders.double("total")
    private val customer_name = Orders.varchar("customer_name", 25)
    private val customer_address = Orders.varchar("customer_address", 150)
    private val customer_email = Orders.varchar("customer_email", 25)

    fun insert(orderDTO: OrderDTO) {
        transaction {
            Orders.insert {
                it[id] = orderDTO.id
                it[book_name] = orderDTO.book_name
                it[quantity] = orderDTO.quantity
                it[price] = orderDTO.price
                it[order_date] = orderDTO.order_date
                it[total] = orderDTO.total
                it[customer_name] = orderDTO.customer_name
                it[customer_address] = orderDTO.customer_address
                it[customer_email] = orderDTO.customer_email
            }
        }
    }

    fun fetchAllOrders(): List<OrderDTO> {
        return try {
            transaction {
                Orders.selectAll().toList()
                    .map {
                        OrderDTO(
                            id = it[Orders.id],
                            book_name = it[Orders.book_name],
                            quantity = it[Orders.quantity],
                            price = it[Orders.price],
                            order_date = it[Orders.order_date],
                            total = it[Orders.total],
                            customer_name = it[Orders.customer_name],
                            customer_address = it[Orders.customer_address],
                            customer_email = it[Orders.customer_email],
                        )
                    }
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    fun fetchOrder(id: Int): OrderDTO? {
        return try {
            transaction {
                val orderModel = Orders.select { Orders.id.eq(id) }.single()
                OrderDTO(
                    id = orderModel[Orders.id],
                    book_name = orderModel[Orders.book_name],
                    quantity = orderModel[Orders.quantity],
                    price = orderModel[Orders.price],
                    order_date = orderModel[Orders.order_date],
                    total = orderModel[Orders.total],
                    customer_name = orderModel[Orders.customer_name],
                    customer_address = orderModel[Orders.customer_address],
                    customer_email = orderModel[Orders.customer_email],
                )
            }
        } catch (e: Exception) {
            null
        }
    }

    fun delete(id: Int): Boolean {
        try {
            transaction {
                Orders.deleteWhere {
                    Orders.id eq id
                }
            }
        } catch (e: Exception){
            return false
        }
        return true
    }


}