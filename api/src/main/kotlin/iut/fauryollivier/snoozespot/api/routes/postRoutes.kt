package iut.fauryollivier.snoozespot.api.routes

import io.ktor.http.HttpStatusCode
import io.ktor.resources.Resource
import io.ktor.server.request.receive
import io.ktor.server.response.*
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.resources.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.parameters.RequestBody
import iut.fauryollivier.snoozespot.api.services.PostService
import kotlinx.serialization.Serializable
import org.koin.ktor.ext.inject

@Serializable
@Resource("")
data class Posts(val page: Int = 0)

@Serializable
@Resource("/{id}")
data class PostById(val id: Int)

@Serializable
data class CreatePostRequest(
    val content: String,
)

fun Route.postRoutes() {
    val postService by inject<PostService>()

    route("/posts") {
        get<Posts> { params ->
            call.respond(postService.getAll(params.page * 20, (params.page + 1) * 20 - 1))
        }

        get<PostById> { params ->
            val post = postService.getById(params.id)
            if (post == null) {
                call.respond(HttpStatusCode.NotFound, "Post not found")
            } else {
                call.respond(post)
            }
        }

        post("") {
            val request = call.receive<CreatePostRequest>()
            try {
                val post = postService.createPost(1, request.content)
                call.respond(HttpStatusCode.Created, post!!)
            } catch (e: Exception) {
                print(e.toString())
                call.respond(HttpStatusCode.BadRequest, "Post creation failed")
            }
        }
    }
}