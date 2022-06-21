package com.coursework.database.books

import java.text.DecimalFormat

@kotlinx.serialization.Serializable
class BookDTO (
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val image_name: String,
    val category_id: Int,
    val featured: String,
    val active: String,
)

