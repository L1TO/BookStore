package com.coursework.database.categories


import com.coursework.features.CategoryInputRemoteModel
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

object Categories: Table("category"){
    private val id = Categories.integer("id")
    private val title = Categories.varchar("title", 25)
    private val image_name = Categories.varchar("image_name", 255)
    private val featured = Categories.varchar("featured", 25)
    private val active = Categories.varchar("active", 25)

    fun insert(categoryDTO: CategoryDTO) {
        transaction {
            Categories.insert {
                it[id] = categoryDTO.id
                it[title] = categoryDTO.title
                it[image_name] = categoryDTO.image_name
                it[featured] = categoryDTO.featured
                it[active] = categoryDTO.active
            }
        }
    }

    fun fetchCategory(id: Int): CategoryDTO? {
        return try {
            transaction {
                val categoryModel = Categories.select {Categories.id.eq(id) }.single()
                CategoryDTO(
                    id = categoryModel[Categories.id],
                    title = categoryModel[Categories.title],
                    image_name = categoryModel[Categories.image_name],
                    featured = categoryModel[Categories.featured],
                    active = categoryModel[Categories.active],
                )
            }
        } catch (e: Exception) {
            null
        }
    }
    fun fetchAllCategories(): List<CategoryDTO> {
        return try {
            transaction {
                Categories.selectAll().toList()
                    .map {
                        CategoryDTO(
                            id = it[Categories.id],
                            title = it[Categories.title],
                            image_name = it[Categories.image_name],
                            featured = it[Categories.featured],
                            active = it[Categories.active],
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
                Categories.deleteWhere {
                    Categories.id eq id
                }
            }
        } catch (e: Exception){
            return false
        }
        return true
    }

    fun updateCategory(categoryDTO: CategoryInputRemoteModel) {
        transaction {
            Categories.update ({ Categories.id eq categoryDTO!!.id }) {
                it[title] = categoryDTO!!.title
                it[image_name] = categoryDTO!!.image_name
                it[featured] = categoryDTO!!.featured
                it[active] = categoryDTO!!.active
            }
        }
    }

}