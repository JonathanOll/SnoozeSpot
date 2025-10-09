package iut.fauryollivier.snoozespot.api.routes

import io.ktor.http.HttpStatusCode
import io.ktor.resources.Resource
import io.ktor.server.response.*
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.resources.get
import io.ktor.server.routing.route
import iut.fauryollivier.snoozespot.api.database.Tables
import iut.fauryollivier.snoozespot.api.models.Post
import iut.fauryollivier.snoozespot.api.services.PostService
import kotlinx.serialization.Serializable
import org.koin.ktor.ext.inject

@Serializable
@Resource("/{id}")
data class PostById(val id: Int)

fun Route.postRoutes() {
    val postService by inject<PostService>()

    route("/posts") {
        get {
            call.respond(postService.getAll())
        }

        get<PostById> { params ->
            val post = postService.getById(params.id)
            if (post == null) {
                call.respond(HttpStatusCode.NotFound, "Post not found")
            } else {
                call.respond(post)
            }
        }
    }
}