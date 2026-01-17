package iut.fauryollivier.snoozespot.api.config

import PostCommentRepository
import SpotCommentRepository
import io.ktor.server.application.*
import iut.fauryollivier.snoozespot.api.auth.jwtModule
import iut.fauryollivier.snoozespot.api.repositories.PostRepository
import iut.fauryollivier.snoozespot.api.repositories.SpotRepository
import iut.fauryollivier.snoozespot.api.repositories.StoredFileRepository
import iut.fauryollivier.snoozespot.api.repositories.UserRepository
import iut.fauryollivier.snoozespot.api.services.*
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin

fun Application.configureKoin() {
    install(Koin) {
        modules(listOf(appModule, jwtModule, fileModule))
    }
}

val fileModule = module {
    single { StoredFileRepository(Config.STORED_FILE_PATH) }
    single { StoredFileService(get()) }
}


val appModule = module {
    single { AuthService(get(), get()) }

    single { UserRepository() }
    single { UserService(get(), get()) }

    single { PostService(get(), get()) }
    single { PostCommentRepository(get()) }
    single { PostRepository(get(), get()) }

    single { SpotService(get(), get()) }
    single { SpotCommentRepository(get()) }
    single { SpotRepository(get(), get(), get()) }

}
