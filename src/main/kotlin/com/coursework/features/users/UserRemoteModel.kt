package com.coursework.features.users

import kotlinx.serialization.Serializable

@Serializable
data class UserLoginRemoteModel(
    val login: String
)

@Serializable
data class UserUpdateRemoteModel(
    val login: String,
    val username: String,
    val email: String

)