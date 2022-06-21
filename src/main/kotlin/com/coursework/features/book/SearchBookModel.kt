package com.coursework.features.book

import kotlinx.serialization.Serializable

@Serializable
data class SearchBookModel(
    val searchQuery: String
)