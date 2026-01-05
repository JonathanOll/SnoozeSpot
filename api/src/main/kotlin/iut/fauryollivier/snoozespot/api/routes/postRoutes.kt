package iut.fauryollivier.snoozespot.api.routes

import io.ktor.http.*
import io.ktor.http.content.PartData
import io.ktor.http.content.forEachPart
import io.ktor.resources.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.utils.io.jvm.javaio.toInputStream
import io.ktor.utils.io.readByteArray
import iut.fauryollivier.snoozespot.api.auth.currentUserId
import iut.fauryollivier.snoozespot.api.dtos.StoredFileDTO
import iut.fauryollivier.snoozespot.api.entities.StoredFile
import iut.fauryollivier.snoozespot.api.enums.StoredFileType
import iut.fauryollivier.snoozespot.api.enums.StoredFileUsage
import iut.fauryollivier.snoozespot.api.repositories.StoredFileRepository
import iut.fauryollivier.snoozespot.api.services.PostService
import iut.fauryollivier.snoozespot.api.services.StoredFileService
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.serialization.Serializable
import org.koin.ktor.ext.inject
import kotlin.Result

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

    post("/image"){
        var description: String = "unknow description"
        var fileResult : Result<StoredFile>? = null

        val multipart = call.receiveMultipart()
        multipart.forEachPart { part ->
            when (part) {
                is PartData.FormItem -> {
                    if (part.name == "description")  {
                        description = part.value
                        if( fileResult != null && fileResult!!.isSuccess && description.isNotBlank()) {
                            storedFileService.changeFileDescription(fileResult!!.getOrThrow().uuid, description)
                        }
                    }
                }
                is PartData.FileItem -> {
                    if (part.name == "image") {
                        fileResult = storedFileService.saveFile(
                            part,
                            description,
                            StoredFileType.IMAGE,
                            StoredFileUsage.POST_MEDIA
                        )
                    }
                    part.dispose()
                }
                else -> part.dispose()
            }
        }

        if ( fileResult == null || fileResult.isFailure) {
            call.respond(HttpStatusCode.BadRequest, "Image upload failed")
            return@post
        }
        call.respond(HttpStatusCode.Created, fileResult.getOrThrow())
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
    }
}