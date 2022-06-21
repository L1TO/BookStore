package com.coursework.features.book

import com.coursework.cache.InMemoryCache
import com.coursework.cache.TokenCache
import com.coursework.features.Book.BookController
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

fun Application.configureBookRouting() {

    routing {
        post ("/addBook") {
            val bookController = BookController(call)
            bookController.addBook()
        }
        get("/books/fetchAll") {
            val bookController = BookController(call)
            bookController.fetchAllBooks()
        }
        post("/books/fetch") {
            val bookController = BookController(call)
            bookController.fetchBooks()
        }
        post("/books/delete") {
            val bookController = BookController(call)
            bookController.deleteBook()
        }
        post("/books/update") {
            val bookController = BookController(call)
            bookController.updateBook()
        }
        post("/books/searchBooks") {
            val bookController = BookController(call)
            bookController.performSearch()
        }
    }
}