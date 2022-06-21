package com.coursework.database.tokens

import com.coursework.database.users.UserDTO
import com.coursework.database.users.Users
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object Tokens: Table("tokens"){
    private val id = Tokens.varchar("id", 50)
    private val login = Tokens.varchar("login", 25)
    private val token = Tokens.varchar("token", 50)


    fun insert(tokenDTO: TokenDTO) {
        transaction {
            Tokens.insert {
                it[id] = tokenDTO.id
                it[login] = tokenDTO.login
                it[token] = tokenDTO.token
            }
        }
    }

    fun fetchToken(token: String): TokenDTO? {
        return try {
            transaction {
                val tokenModel = Tokens.select { Tokens.token.eq(token) }.single()
                TokenDTO(
                    login = tokenModel[Tokens.login],
                    id = tokenModel[Tokens.id],
                    token = tokenModel[Tokens.token]
                )
            }
        } catch (e: Exception) {
            null
        }
    }
}