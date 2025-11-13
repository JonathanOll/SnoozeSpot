package iut.fauryollivier.snoozespot.api.routes

import io.ktor.server.response.*
import io.ktor.server.routing.*
import iut.fauryollivier.snoozespot.api.services.UserService
import org.koin.ktor.ext.inject

// route("/users")
fun Route.userRoutes() {
    val userService by inject<UserService>()

    get {
        call.respond(userService.getAll())
    }
}