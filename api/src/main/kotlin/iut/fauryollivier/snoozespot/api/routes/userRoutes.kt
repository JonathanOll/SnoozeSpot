package iut.fauryollivier.snoozespot.api.routes

import io.ktor.http.HttpStatusCode
import io.ktor.server.auth.authenticate
import io.ktor.server.response.*
import io.ktor.server.routing.*
import iut.fauryollivier.snoozespot.api.auth.currentUserId
import iut.fauryollivier.snoozespot.api.auth.handleMultipart
import iut.fauryollivier.snoozespot.api.services.StoredFileService
import iut.fauryollivier.snoozespot.api.services.UserService
import org.koin.ktor.ext.inject
import java.util.UUID

// route("/users")
fun Route.userRoutes() {
    val userService by inject<UserService>()
    val storedFileService by inject<StoredFileService>()

    get {
        val usersResult = userService.getAll()
        if (usersResult.isFailure) {
            call.respond(HttpStatusCode.InternalServerError)
            return@get
        }
        call.respond(usersResult.getOrThrow())
    }

    get("/{uuid}") {
        val uuid = try {
            UUID.fromString(call.parameters["uuid"].toString())
        } catch (e: IllegalArgumentException) {
            call.respond(HttpStatusCode.BadRequest, "Invalid user ID")
            return@get
        }

        val result = userService.getByUuid(uuid)
        if (result.isFailure) {
            call.respond(HttpStatusCode.NotFound, "User not found")
            return@get
        }
        call.respond(result.getOrThrow())
    }
    authenticate("jwtAuth") {
        post("profile-picture") {
            val userId = call.currentUserId().getOrThrow()

            val (_, files) = call.handleMultipart<Unit>(storedFileService)

            if (files.isEmpty()) {
                call.respond(HttpStatusCode.BadRequest, "No file uploaded")
                return@post
            }

            val fileResult = files.first()
            if (fileResult.isFailure) {
                call.respond(HttpStatusCode.BadRequest, "File upload failed")
                return@post
            }

            val fileId = fileResult.getOrThrow().id

            val result = userService.changeProfilePicture(userId, fileId!!)
            if (result.isFailure) {
                call.respond(HttpStatusCode.BadRequest, "Could not update profile picture")
                return@post
            }

            call.respond(HttpStatusCode.OK, "Profile picture updated")
        }
    }


}