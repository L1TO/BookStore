package com.coursework.database.orders

import java.util.DoubleSummaryStatistics

@kotlinx.serialization.Serializable
class OrderDTO (
    val id: Int,
    val book_name: String,
    val price: Double,
    val quantity: Int,
    val order_date: String,
    val total: Double,
    val customer_address: String,
    val customer_name: String,
    val customer_email: String,
)

