package com.coursework.features.category

import com.coursework.cache.InMemoryCache
import com.coursework.cache.TokenCache
import com.coursework.features.LoginInputRemoteModel
import com.coursework.features.LoginOutputRemoteModel
import com.coursework.features.login.LoginController
import com.coursework.features.users.UsersController
import com.coursework.plugins.Obj
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.UUID

fun Application.configureCategoryRouting() {

    routing {
        post ("/addCategory") {
            val categoryController = CategoryController(call)
            categoryController.addCategory()
        }
        get("/categories/fetchAll") {
            val categoryController = CategoryController(call)
            categoryController.fetchAllCategories()
        }
        post("/categories/fetch") {
            val categoryController = CategoryController(call)
            categoryController.fetchCategories()
        }
        post("/categories/delete") {
            val categoryController = CategoryController(call)
            categoryController.deleteCategory()
        }
        post("/categories/update") {
            val categoryController = CategoryController(call)
            categoryController.updateCategory()
        }
    }
}