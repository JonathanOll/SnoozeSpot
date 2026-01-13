package iut.fauryollivier.snoozespot.api.auth

import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import iut.fauryollivier.snoozespot.api.config.Config
import iut.fauryollivier.snoozespot.api.entities.StoredFile
import iut.fauryollivier.snoozespot.api.enums.StoredFileType
import iut.fauryollivier.snoozespot.api.enums.StoredFileUsage
import iut.fauryollivier.snoozespot.api.repositories.UserRepository
import iut.fauryollivier.snoozespot.api.services.StoredFileService
import org.koin.dsl.module
import org.koin.ktor.ext.inject
import java.util.*
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor

fun Application.configureSecurity() {
    val jwtService by inject<JWTService>()

    install(Authentication) {
        jwt("jwtAuth") {
            verifier(jwtService.verifier())
            validate { credential ->
                credential.payload.subject ?: return@validate null
                JWTPrincipal(credential.payload)
            }
            challenge { _, _ -> call.respond(HttpStatusCode.Unauthorized, "Invalid or missing token") }
        }
    }
}

val jwtModule = module {

    single {
        JWTService(Config.TOKEN_SECRET, Config.TOKEN_ISSUER, Config.TOKEN_EXPIRATION)
    }
}

fun ApplicationCall.currentUserId(): Result<Int> {
    val uuidStr = principal<JWTPrincipal>()?.payload?.subject ?: return Result.failure(Exception("Unauthorized"))
    val uuid = try {
        UUID.fromString(uuidStr)
    } catch (e: IllegalArgumentException) {
        return Result.failure(Exception("Invalid token"))
    }
    val userRepository by inject<UserRepository>()
    val idResult = userRepository.getUserIdByUUID(uuid)
    if (idResult.isFailure) return Result.failure(Exception("Unauthorized"))
    return Result.success(idResult.getOrThrow())
}

suspend inline fun <reified T : Any> ApplicationCall.handleMultipart(
    storedFileService: StoredFileService,
    description: String = "no description"
): Pair<T, List<Result<StoredFile>>> {

    val multipart = receiveMultipart()
    val params = mutableMapOf<String, String>()
    val files = mutableListOf<Result<StoredFile>>()

    multipart.forEachPart { part ->
        when (part) {

            is PartData.FormItem -> {
                if (part.name != null) {
                    params[part.name!!] = part.value
                }
            }

            is PartData.FileItem -> {
                files += storedFileService.saveFile(
                    part,
                    description,
                    StoredFileType.IMAGE,
                    StoredFileUsage.POST_MEDIA
                )
            }

            else -> {}
        }
        part.dispose()
    }

    val typedObject = if (T::class != Unit::class) mapParamsToClass(params, T::class) else Unit as T
    return typedObject to files
}

fun <T : Any> mapParamsToClass(params: Map<String, String>, clazz: KClass<T>): T {
    val constructor = clazz.primaryConstructor
        ?: throw IllegalArgumentException("Class must have a primary constructor")

    val args = constructor.parameters.associateWith { param ->
        val rawValue = params[param.name]

        when (param.type.classifier) {
            String::class -> rawValue
            Int::class -> rawValue?.toInt()
            Double::class -> rawValue?.toDouble()
            Float::class -> rawValue?.toFloat()
            Boolean::class -> rawValue?.toBoolean()
            Long::class -> rawValue?.toLong()
            else -> throw IllegalArgumentException("Unsupported type: ${param.type}")
        }
    }

    return constructor.callBy(args)
}

