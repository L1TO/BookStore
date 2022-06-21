package com.coursework.features.registration

import kotlinx.serialization.Serializable
import javax.print.attribute.standard.JobOriginatingUserName

@Serializable
data class RegisterInputRemoteModel(
    val login: String,
    val email: String,
    val password: String,
    val username: String
)

@Serializable
data class RegisterOutputRemoteModel(
    val token: String
)