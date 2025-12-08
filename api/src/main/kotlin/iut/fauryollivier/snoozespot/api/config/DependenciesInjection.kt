package iut.fauryollivier.snoozespot.api.config

import PostCommentRepository
import SpotCommentRepository
import io.ktor.server.application.*
import iut.fauryollivier.snoozespot.api.auth.jwtModule
import iut.fauryollivier.snoozespot.api.entities.Spot
import iut.fauryollivier.snoozespot.api.repositories.PostRepository
import iut.fauryollivier.snoozespot.api.repositories.SpotRepository
import iut.fauryollivier.snoozespot.api.repositories.UserRepository
import iut.fauryollivier.snoozespot.api.services.AuthService
import iut.fauryollivier.snoozespot.api.services.PostService
import iut.fauryollivier.snoozespot.api.services.SpotService
import iut.fauryollivier.snoozespot.api.services.UserService
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin

fun Application.configureKoin() {
    install(Koin) {
        modules(listOf(appModule, jwtModule))
    }
}

val appModule = module {
    single { AuthService(get(), get()) }

    single { UserRepository() }
    single { UserService(get()) }

    single { PostService(get()) }
    single { PostCommentRepository(get()) }
    single { PostRepository(get()) }

    single { SpotService(get()) }
    single { SpotCommentRepository(get()) }
    single { SpotRepository(get(), get()) }

}
