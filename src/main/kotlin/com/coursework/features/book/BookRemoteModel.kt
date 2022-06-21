package com.coursework.features

import kotlinx.serialization.Serializable

@Serializable
data class BookInputRemoteModel(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val image_name: String,
    val category_id: Int,
    val featured: String,
    val active: String,
)

@Serializable
data class BookIdModel(
    val id: Int,
)
