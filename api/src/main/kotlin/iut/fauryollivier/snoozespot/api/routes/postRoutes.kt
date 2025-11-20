package iut.fauryollivier.snoozespot.api.routes

import io.ktor.http.*
import io.ktor.resources.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import iut.fauryollivier.snoozespot.api.auth.currentUserId
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

// route("/posts")
fun Route.postRoutes() {
    val postService by inject<PostService>()

    get<Posts> { params ->
        val postsResult = postService.getAll(params.page * 20, (params.page + 1) * 20 - 1)
        if (postsResult.isFailure) {
            call.respond(HttpStatusCode.InternalServerError)
            return@get
        }
        call.respond(postsResult.getOrThrow())
    }

    get<PostById> { params ->
        val postResult = postService.getById(params.id)
        if (postResult.isFailure) {
            call.respond(HttpStatusCode.NotFound, "Post not found")
            return@get
        }
        call.respond(postResult.getOrThrow())
    }

    authenticate("jwtAuth") {
        post("") {
            val userId = call.currentUserId().getOrThrow()
            val request = call.receive<CreatePostRequest>()
            val postResult = postService.createPost(userId, request.content)
            if (postResult.isFailure) {
                call.respond(HttpStatusCode.BadRequest, "Post creation failed")
                return@post
            }
            call.respond(HttpStatusCode.Created, postResult.getOrThrow())
        }
    }
}