package com.coursework.features

import kotlinx.serialization.Serializable

@Serializable
data class CategoryInputRemoteModel(
    val id: Int,
    val title: String,
    val image_name: String,
    val featured: String,
    val active: String
)

@Serializable
data class CategoryIdModel(
    val id: Int,
)
