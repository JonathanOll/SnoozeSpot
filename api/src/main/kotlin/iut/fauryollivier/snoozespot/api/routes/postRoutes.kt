package iut.fauryollivier.snoozespot.api.routes

import io.ktor.http.*
import io.ktor.resources.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import iut.fauryollivier.snoozespot.api.auth.currentUserId
import iut.fauryollivier.snoozespot.api.auth.handleMultipart
import iut.fauryollivier.snoozespot.api.services.PostService
import iut.fauryollivier.snoozespot.api.services.StoredFileService
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

@Serializable
data class CreatePostCommentRequest(
    val content: String,
)

// route("/posts")
fun Route.postRoutes() {
    val postService by inject<PostService>()
    val storedFileService by inject<StoredFileService>()

    authenticate("jwtAuth", optional = true) {
        get<Posts> { params ->
            val userId = call.currentUserId().getOrNull()

            val postsResult = postService.getAll(params.page * 20, (params.page + 1) * 20 - 1, userId)
            if (postsResult.isFailure) {
                call.respond(HttpStatusCode.InternalServerError)
                return@get
            }
            call.respond(postsResult.getOrThrow())
        }

        get<PostById> { params ->
            val userId = call.currentUserId().getOrNull()

            val postResult = postService.getById(params.id, userId)
            if (postResult.isFailure) {
                call.respond(HttpStatusCode.NotFound, "Post not found")
                return@get
            }
            call.respond(postResult.getOrThrow())
        }
    }

    authenticate("jwtAuth") {
        post("") {
            val userId = call.currentUserId().getOrThrow()

            val (request, files) = call.handleMultipart<CreatePostRequest>(storedFileService)

            val postResult = postService.createPost(userId, request.content, files)
            if (postResult.isFailure) {
                call.respond(HttpStatusCode.BadRequest, "Post creation failed")
                return@post
            }
            call.respond(HttpStatusCode.Created, postResult.getOrThrow())
        }

        post("{id}/like") {
            val userId = call.currentUserId().getOrNull()
            val postId = call.parameters["id"]?.toIntOrNull()

            if (postId == null) {
                call.respond(HttpStatusCode.BadRequest, "Post not found")
            }
            val postResult = postService.getById(postId!!, userId)
            if (postResult.isFailure) {
                call.respond(HttpStatusCode.BadRequest, "Post not found")
            }

            val result = postService.likePost(postId, userId!!)
            if (result.isFailure) {
                call.respond(HttpStatusCode.BadRequest, "Could not like post $postId")
                return@post
            }
            call.respond(HttpStatusCode.OK, result.getOrThrow())
        }

        post("{id}/comment") {
            val userId = call.currentUserId().getOrNull()
            val postId = call.parameters["id"]?.toIntOrNull()

            if (postId == null) {
            call.respond(HttpStatusCode.BadRequest, "Post not found")
        }
            val postResult = postService.getById(postId!!, userId)
            if (postResult.isFailure) {
                call.respond(HttpStatusCode.BadRequest, "Post not found")
            }

            val request = call.receive<CreatePostCommentRequest>()

            val result = postService.createPostComment(userId!!, postId, request.content)
            if (result.isFailure) {
                call.respond(HttpStatusCode.BadRequest, "Could not comment post $postId")
                return@post
            }
            call.respond(HttpStatusCode.OK, result.getOrThrow())
        }

        delete("{id}") {
            val userId = call.currentUserId().getOrNull()
            val postId = call.parameters["id"]?.toIntOrNull()

            if (postId == null) {
                call.respond(HttpStatusCode.BadRequest, "Post not found")
            }
            val postResult = postService.getById(postId!!, userId)
            if (postResult.isFailure) {
                call.respond(HttpStatusCode.BadRequest, "Post not found")
            }

            val result = postService.deletePost(postId, userId!!)
            if (result.isFailure) {
                call.respond(HttpStatusCode.BadRequest, "Could not delete post $postId")
                return@delete
            }
            call.respond(HttpStatusCode.OK)
        }

        delete("comment/{commentId}") {
            val userId = call.currentUserId().getOrNull()
            val commentId = call.parameters["commentId"]?.toIntOrNull()

            if (commentId == null) {
                call.respond(HttpStatusCode.BadRequest, "Comment not found")
            }

            val result = postService.deletePostComment(commentId!!, userId!!)
            if (result.isFailure) {
                call.respond(HttpStatusCode.BadRequest, "Could not delete comment $commentId")
                return@delete
            }
            call.respond(HttpStatusCode.OK, result.getOrThrow())
        }
    }
}