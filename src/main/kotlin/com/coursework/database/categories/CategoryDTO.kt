package com.coursework.database.categories

@kotlinx.serialization.Serializable
class CategoryDTO (
    val id: Int,
    val title: String,
    val image_name: String,
    val featured: String,
    val active: String
)

