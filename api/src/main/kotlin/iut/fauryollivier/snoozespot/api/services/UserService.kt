package iut.fauryollivier.snoozespot.api.services

import iut.fauryollivier.snoozespot.api.models.User
import iut.fauryollivier.snoozespot.api.repositories.UserRepository

class UserService(private val userRepository: UserRepository) {

    fun getAll(): List<User> {
        return userRepository.getAll()
    }
}