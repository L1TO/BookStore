package com.coursework.database.users

@kotlinx.serialization.Serializable
class UserDTO (
    val login: String,
    val password: String,
    val username: String,
    val email: String
)

@kotlinx.serialization.Serializable
class UserUpdateDTO (
    val login: String,
    val username: String,
    val email: String
)

@kotlinx.serialization.Serializable
class UserLoginDTO (
    val login: String,
)
