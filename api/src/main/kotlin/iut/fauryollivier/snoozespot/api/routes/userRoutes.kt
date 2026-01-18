package iut.fauryollivier.snoozespot.api.routes

import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import iut.fauryollivier.snoozespot.api.auth.currentUserId
import iut.fauryollivier.snoozespot.api.auth.handleMultipart
import iut.fauryollivier.snoozespot.api.services.StoredFileService
import iut.fauryollivier.snoozespot.api.services.UserService
import org.koin.ktor.ext.inject
import java.util.*

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

    authenticate("jwtAuth", optional = true) {
        get("/{uuid}") {
            val uuid = try {
                UUID.fromString(call.parameters["uuid"].toString())
            } catch (e: IllegalArgumentException) {
                call.respond(HttpStatusCode.BadRequest, "Invalid user ID")
                return@get
            }

            val userId = call.currentUserId().getOrNull()

            val result = userService.getByUuid(uuid, userId)
            if (result.isFailure) {
                call.respond(HttpStatusCode.NotFound, "User not found")
                return@get
            }
            call.respond(result.getOrThrow())
        }
    }

    authenticate("jwtAuth") {
        get("me") {
            val userId = call.currentUserId().getOrThrow()

            val result = userService.getById(userId)
            if (result.isFailure) {
                call.respond(HttpStatusCode.NotFound, "User not found")
                return@get
            }
            call.respond(result.getOrThrow())
        }

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

            call.respond(HttpStatusCode.OK, result.getOrThrow())
        }

        post("/{uuid}/follow") {
            val currentUserId = call.currentUserId().getOrThrow()

            val uuid = try {
                UUID.fromString(call.parameters["uuid"])
            } catch (e: Exception) {
                call.respond(HttpStatusCode.BadRequest, "Invalid UUID")
                return@post
            }

            val targetIdResult = userService.getUserIdByUUID(uuid)
            if (targetIdResult.isFailure) {
                call.respond(HttpStatusCode.NotFound, "User not found")
                return@post
            }

            val targetId = targetIdResult.getOrThrow()

            val result = userService.followUser(currentUserId, targetId)
            if (result.isFailure) {
                call.respond(HttpStatusCode.BadRequest, result.exceptionOrNull()!!.message!!)
                return@post
            }

            call.respond(HttpStatusCode.OK, "You now follow user $uuid")
        }

        delete("/{uuid}/unfollow") {
            val currentUserId = call.currentUserId().getOrThrow()

            val uuid = try {
                UUID.fromString(call.parameters["uuid"])
            } catch (e: Exception) {
                call.respond(HttpStatusCode.BadRequest, "Invalid UUID")
                return@delete
            }

            val targetIdResult = userService.getUserIdByUUID(uuid)
            if (targetIdResult.isFailure) {
                call.respond(HttpStatusCode.NotFound, "User not found")
                return@delete
            }

            val targetId = targetIdResult.getOrThrow()

            val result = userService.unfollowUser(currentUserId, targetId)
            if (result.isFailure) {
                call.respond(HttpStatusCode.BadRequest, result.exceptionOrNull()!!.message!!)
                return@delete
            }

            call.respond(HttpStatusCode.OK, "You no longer follow user $uuid")
        }

        get("/following") {
            val currentUserId = call.currentUserId().getOrThrow()

            val result = userService.getFollowing(currentUserId)
            if (result.isFailure) {
                call.respond(HttpStatusCode.InternalServerError, "Could not fetch following list")
                return@get
            }

            call.respond(HttpStatusCode.OK, result.getOrThrow())
        }

    }
}