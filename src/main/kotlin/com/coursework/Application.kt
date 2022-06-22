package com.coursework

import com.coursework.features.category.configureCategoryRouting
import com.coursework.features.book.configureBookRouting
import com.coursework.features.login.configureLoginRouting
import com.coursework.features.order.configureOrderRouting
import com.coursework.features.registration.configureRegistrationRouting
import com.coursework.features.users.configureUsersRouting
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.coursework.plugins.*
import org.jetbrains.exposed.sql.Database

fun main() {
    Database.connect("jdbc:mysql://oliadkuxrl9xdugh.chr7pe7iynqr.eu-west-1.rds.amazonaws.com:3306/st9qlw1hvzs8hawd",
        "com.mysql.cj.jdbc.Driver",
        "t066ywiahbol8mu7",
        "cfj5g7h86s9gbgyj")

    embeddedServer(Netty, port = 8080 ){
        configureRouting()
        configureLoginRouting()
        configureRegistrationRouting()
        configureSerialization()
        configureUsersRouting()
        configureCategoryRouting()
        configureBookRouting()
        configureOrderRouting()
    }.start(wait = true)
}
