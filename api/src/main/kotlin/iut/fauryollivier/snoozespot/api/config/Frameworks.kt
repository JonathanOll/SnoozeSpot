package iut.fauryollivier.snoozespot.api.config

import io.ktor.server.application.*
import io.ktor.server.plugins.di.*
import iut.fauryollivier.snoozespot.api.GreetingService

fun Application.configureFrameworks() {
    dependencies {
        provide { GreetingService { "Hello, World!" } }
    }
}
