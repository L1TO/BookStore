package com.coursework.features.Book

import com.coursework.cache.InMemoryCache
import com.coursework.cache.TokenCache
import com.coursework.database.books.BookDTO
import com.coursework.database.books.Books
import com.coursework.database.tokens.TokenDTO
import com.coursework.database.tokens.Tokens
import com.coursework.database.users.UserDTO
import com.coursework.database.users.Users
import com.coursework.features.BookIdModel
import com.coursework.features.BookInputRemoteModel
import com.coursework.features.LoginInputRemoteModel
import com.coursework.features.LoginOutputRemoteModel
import com.coursework.features.book.SearchBookModel
import com.coursework.features.users.UserLoginRemoteModel
import com.coursework.features.users.UserUpdateRemoteModel
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.jetbrains.exposed.exceptions.ExposedSQLException
import java.util.*

class BookController(private val call: ApplicationCall) {


    suspend fun addBook() {
        val receive = call.receive<BookInputRemoteModel>()
        val bookDTO = Books.fetchBook(receive.id)

        if (bookDTO != null) {
            call.respond(HttpStatusCode.BadRequest, "Book already exists")
        } else {
            try {
                Books.insert(
                    BookDTO(
                        id = receive.id,
                        title = receive.title,
                        description = receive.description,
                        price = receive.price,
                        image_name = receive.image_name,
                        category_id = receive.category_id,
                        featured = receive.featured,
                        active = receive.active,
                    )
                )
                call.respond(receive.id)
            } catch (e: ExposedSQLException) {
                call.respond(HttpStatusCode.Conflict, "Book already exists")
            }
        }
    }

    suspend fun fetchAllBooks() {
        call.respond(Books.fetchAllBooks())
    }

    suspend fun fetchBooks(){
        val receive = call.receive<BookIdModel>()
        val result = Books.fetchBook(receive.id)
        if (result != null){
            call.respond(result)
        } else {
            call.respond(HttpStatusCode.BadRequest, "Id incorrect")
        }
    }

    suspend fun updateBook() {
        val receive = call.receive<BookInputRemoteModel>()
        val BookDTO = Books.fetchBook(receive.id)
        if (BookDTO != null){
            val BookUpdateDTO = Books.updateBook(receive)
            call.respond(BookUpdateDTO)
        } else{
            call.respond(HttpStatusCode.BadRequest)
        }
    }

    suspend fun deleteBook() {
        val receive = call.receive<BookIdModel>()
        val BookDTO = Books.fetchBook(receive.id)
        if (BookDTO != null) {
            Books.delete(BookDTO.id)
            call.respond(HttpStatusCode.OK)
        } else {
            call.respond(HttpStatusCode.BadRequest)
        }
    }

    suspend fun performSearch() {
        val request = call.receive<SearchBookModel>()

        if (request != null) {
            call.respond(Books.fetchAllBooks().filter { it.title.contains(request.searchQuery, ignoreCase = true) })
        } else {
            call.respond(HttpStatusCode.Unauthorized, "Books not found")
        }
    }
}