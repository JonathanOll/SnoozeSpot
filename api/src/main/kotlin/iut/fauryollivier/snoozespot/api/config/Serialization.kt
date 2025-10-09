package iut.fauryollivier.snoozespot.api.config

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import java.time.LocalDateTime

fun Application.configureSerialization() {
    val module = SerializersModule {
        contextual(LocalDateTime::class, LocalDateTimeSerializer)
    }

    install(ContentNegotiation) {
        json(
            Json {
                serializersModule = module
                prettyPrint = true
                encodeDefaults = true
            }
        )
    }
}
