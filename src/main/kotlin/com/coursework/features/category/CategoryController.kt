package com.coursework.features.category

import com.coursework.cache.InMemoryCache
import com.coursework.cache.TokenCache
import com.coursework.database.categories.Categories
import com.coursework.database.categories.CategoryDTO
import com.coursework.database.tokens.TokenDTO
import com.coursework.database.tokens.Tokens
import com.coursework.database.users.UserDTO
import com.coursework.database.users.Users
import com.coursework.features.CategoryIdModel
import com.coursework.features.CategoryInputRemoteModel
import com.coursework.features.LoginInputRemoteModel
import com.coursework.features.LoginOutputRemoteModel
import com.coursework.features.users.UserLoginRemoteModel
import com.coursework.features.users.UserUpdateRemoteModel
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.jetbrains.exposed.exceptions.ExposedSQLException
import java.util.*

class CategoryController(private val call: ApplicationCall) {


    suspend fun addCategory() {
        val receive = call.receive<CategoryInputRemoteModel>()
        val categoryDTO = Categories.fetchCategory(receive.id)

        if (categoryDTO != null) {
            call.respond(HttpStatusCode.BadRequest, "Category already exists")
        } else {
            try {
                Categories.insert(
                    CategoryDTO(
                        id = receive.id,
                        title = receive.title,
                        image_name = receive.image_name,
                        featured = receive.featured,
                        active = receive.active,
                    )
                )
                call.respond(receive.id)
            } catch (e: ExposedSQLException) {
                call.respond(HttpStatusCode.Conflict, "User already exists")
            }
        }
    }

    suspend fun fetchAllCategories() {
        call.respond(Categories.fetchAllCategories())
    }

    suspend fun fetchCategories(){
        val receive = call.receive<CategoryIdModel>()
        val result = Categories.fetchCategory(receive.id)
        if (result != null){
            call.respond(result)
        } else {
            call.respond(HttpStatusCode.BadRequest, "Id incorrect")
        }
    }

    suspend fun updateCategory() {
        val receive = call.receive<CategoryInputRemoteModel>()
        val categoryDTO = Categories.fetchCategory(receive.id)
        if (categoryDTO != null){
            val categoryUpdateDTO = Categories.updateCategory(receive)
            call.respond(categoryUpdateDTO)
        } else{
            call.respond(HttpStatusCode.BadRequest)
        }
    }

    suspend fun deleteCategory() {
        val receive = call.receive<CategoryIdModel>()
        val categoryDTO = Categories.fetchCategory(receive.id)
        if (categoryDTO != null) {
            Categories.delete(categoryDTO.id)
            call.respond(HttpStatusCode.OK)
        } else {
            call.respond(HttpStatusCode.BadRequest)
        }
    }
}