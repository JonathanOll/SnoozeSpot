package iut.fauryollivier.snoozespot.api

import iut.fauryollivier.snoozespot.api.database.configureORM
import io.ktor.server.application.*
import io.ktor.server.netty.EngineMain

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
