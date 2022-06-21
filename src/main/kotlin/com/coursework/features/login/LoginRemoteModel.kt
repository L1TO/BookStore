package com.coursework.features

import kotlinx.serialization.Serializable

@Serializable
data class LoginInputRemoteModel(
    val login: String,
    val password: String
)

@Serializable
data class LoginOutputRemoteModel(
    val token: String
)