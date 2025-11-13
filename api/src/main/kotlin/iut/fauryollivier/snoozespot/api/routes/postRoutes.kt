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

    authenticate("jwtAuth") {
        post("") {
            val userId = call.currentUserId().getOrThrow()
            val request = call.receive<CreatePostRequest>()
            try {
                val post = postService.createPost(userId, request.content)
                call.respond(HttpStatusCode.Created, post!!)
            } catch (e: Exception) {
                print(e.toString())
                call.respond(HttpStatusCode.BadRequest, "Post creation failed")
            }
        }
    }
}