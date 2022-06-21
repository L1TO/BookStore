package com.coursework.database.users

import com.coursework.features.users.UserUpdateRemoteModel
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

object Users: Table("users"){
    private val login = Users.varchar("login", 25)
    private val password = Users.varchar("password", 25)
    private val username = Users.varchar("username", 25)
    private val email = Users.varchar("email", 25)

    fun insert(userDTO: UserDTO) {
        transaction {
            Users.insert {
                it[login] = userDTO.login
                it[password] = userDTO.password
                it[username] = userDTO.username
                it[email] = userDTO.email
            }
        }
    }

    fun update(userDTO: UserUpdateRemoteModel?) {
            transaction {
                Users.update ({ Users.login eq userDTO!!.login }) {
                    it[username] = userDTO!!.username
                    it[email] = userDTO!!.email
                }
            }
    }

    fun delete(login: String): Boolean {
        try {
            transaction {
                Users.deleteWhere {
                    Users.login eq login
                }
            }
        } catch (e: Exception){
            return false
        }
        return true
    }

    fun fetchUser(login: String): UserDTO? {
        return try {
            transaction {
                val userModel = Users.select { Users.login.eq(login) }.single()
                UserDTO(
                    login = userModel[Users.login],
                    password = userModel[password],
                    username = userModel[username],
                    email = userModel[email]
                )
            }
        } catch (e: Exception) {
            null
        }
    }

    fun fetchAllUsers(): List<UserDTO> {
        return try {
            transaction {
                Users.selectAll().toList()
                    .map {
                        UserDTO(
                            login = it[Users.login],
                            password = it[password],
                            username = it[username],
                            email = it[email]
                        )
                    }
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}