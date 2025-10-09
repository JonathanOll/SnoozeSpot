package iut.fauryollivier.snoozespot.api

import iut.fauryollivier.snoozespot.api.database.configureORM
import io.ktor.server.application.*
import io.ktor.server.netty.EngineMain
import iut.fauryollivier.snoozespot.api.config.configureFrameworks
import iut.fauryollivier.snoozespot.api.config.configureHTTP
import iut.fauryollivier.snoozespot.api.config.configureMonitoring
import iut.fauryollivier.snoozespot.api.config.configureSecurity
import iut.fauryollivier.snoozespot.api.config.configureSerialization

fun main(args: Array<String>) {
    EngineMain.main(args)
}

fun Application.module() {
    configureORM()
    configureFrameworks()
    configureSerialization()
    configureMonitoring()
    configureSecurity()
    configureHTTP()
    configureRouting()
}
