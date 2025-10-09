package iut.fauryollivier.snoozespot.api.routes

import io.ktor.server.response.*
import io.ktor.server.routing.*
import iut.fauryollivier.snoozespot.api.services.UserService
import org.koin.ktor.ext.inject

fun Route.userRoutes() {
    val userService by inject<UserService>()

    route("/users") {
        get {
            call.respond(userService.getAll())
        }
    }
}