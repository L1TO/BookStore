package com.coursework.cache

import com.coursework.features.registration.RegisterInputRemoteModel

object InMemoryCache {
    val userList: MutableList<RegisterInputRemoteModel> = mutableListOf()
    val token: MutableList<TokenCache> = mutableListOf()
}


data class TokenCache(
    val login: String,
    val token: String
)