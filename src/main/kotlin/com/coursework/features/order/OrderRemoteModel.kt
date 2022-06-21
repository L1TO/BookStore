package com.coursework.features

import kotlinx.serialization.Serializable

@Serializable
data class OrderInputRemoteModel(
    val id: Int,
    val book_name: String,
    val price: Double,
    val quantity: Int,
    val order_date: String,
    val total: Double,
    val customer_name: String,
    val customer_address: String,
    val customer_email: String,
)

@Serializable
data class OrderIdModel(
    val id: Int,
)
