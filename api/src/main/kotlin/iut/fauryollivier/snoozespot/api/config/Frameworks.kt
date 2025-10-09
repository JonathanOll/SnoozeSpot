package iut.fauryollivier.snoozespot.api.config

import io.ktor.server.application.*
import iut.fauryollivier.snoozespot.api.repositories.UserRepository
import iut.fauryollivier.snoozespot.api.services.UserService
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin

fun Application.configureKoin() {
    install(Koin) {
        modules(appModule)
    }
}

val appModule = module {
    single { UserRepository() }
    single { UserService(get()) }
}
