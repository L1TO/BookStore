package com.coursework.database.books


import com.coursework.features.BookInputRemoteModel
import com.coursework.features.CategoryInputRemoteModel
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

object Books: Table("book"){
    private val id = Books.integer("id")
    private val title = Books.varchar("title", 25)
    private val description = Books.varchar("description", 255)
    private val price = Books.double("price")
    private val image_name = Books.varchar("image_name", 255)
    private val category_id = Books.integer("category_id")
    private val featured = Books.varchar("featured", 10)
    private val active = Books.varchar("active", 10)

    fun insert(bookDTO: BookDTO) {
        transaction {
            Books.insert {
                it[id] = bookDTO.id
                it[title] = bookDTO.title
                it[description] = bookDTO.description
                it[price] = bookDTO.price
                it[image_name] = bookDTO.image_name
                it[category_id] = bookDTO.category_id
                it[featured] = bookDTO.featured
                it[active] = bookDTO.active
            }
        }
    }

    fun fetchBook(id: Int): BookDTO? {
        return try {
            transaction {
                val bookModel = Books.select {Books.id.eq(id) }.single()
                BookDTO(
                    id = bookModel[Books.id],
                    title = bookModel[Books.title],
                    description = bookModel[Books.description],
                    price = bookModel[Books.price],
                    image_name = bookModel[Books.image_name],
                    category_id = bookModel[Books.category_id],
                    featured = bookModel[Books.featured],
                    active = bookModel[Books.active],
                )
            }
        } catch (e: Exception) {
            null
        }
    }
    fun fetchAllBooks(): List<BookDTO> {
        return try {
            transaction {
                Books.selectAll().toList()
                    .map {
                        BookDTO(
                            id = it[Books.id],
                            title = it[Books.title],
                            description = it[Books.description],
                            price = it[Books.price],
                            image_name = it[Books.image_name],
                            category_id = it[Books.category_id],
                            featured = it[Books.featured],
                            active = it[Books.active],
                        )
                    }
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    fun delete(id: Int): Boolean {
        try {
            transaction {
                Books.deleteWhere {
                    Books.id eq id
                }
            }
        } catch (e: Exception){
            return false
        }
        return true
    }

    fun updateBook(bookDTO: BookInputRemoteModel) {
        transaction {
            Books.update ({ Books.id eq bookDTO!!.id }) {
                it[title] = bookDTO!!.title
                it[description] = bookDTO!!.description
                it[price] = bookDTO!!.price
                it[image_name] = bookDTO!!.image_name
                it[category_id] = bookDTO!!.category_id
                it[featured] = bookDTO!!.featured
                it[active] = bookDTO!!.active
            }
        }
    }

}