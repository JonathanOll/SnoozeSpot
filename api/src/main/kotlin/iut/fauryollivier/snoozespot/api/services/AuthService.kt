package iut.fauryollivier.snoozespot.api.services


import iut.fauryollivier.snoozespot.api.routes.AuthResponse
import iut.fauryollivier.snoozespot.api.auth.JWTService
import iut.fauryollivier.snoozespot.api.auth.Password
import iut.fauryollivier.snoozespot.api.auth.model.UserAuthRequest
import iut.fauryollivier.snoozespot.api.models.User
import iut.fauryollivier.snoozespot.api.repositories.UserRepository
import kotlin.Result

class AuthService(private val userRepository:UserRepository, private val jwtService: JWTService) {

     fun create(userAuth:UserAuthRequest): Result<User> {
         val userResult = userRepository.getByUsername(userAuth.username)
         if(userResult.isSuccess) { //User already exists
             return Result.failure(Exception("Invalid credentials"))
         }

         val result = userRepository.create(userAuth)
         if(result.isFailure) return Result.failure(Exception("Invalid credentials"))

         val user = userRepository.getById(result.getOrThrow())
         if(user.isFailure) return Result.failure(Exception("User create but not found"))
         return Result.success(user.getOrThrow())
     }

     fun login(request: UserAuthRequest): Result<AuthResponse> {
         val passwordResult = userRepository.getUsernamePasswordHash(request.username)
         if (passwordResult.isFailure || !Password.verify(request.password, passwordResult.getOrThrow())) {
             return Result.failure(Exception("Invalid credentials"))
         }

         val userResult = userRepository.getByUsername(request.username)
         if(userResult.isFailure) return Result.failure(Exception("Invalid credentials"))
         val token = jwtService.generateToken(userResult.getOrThrow().uuid)
         return Result.success(AuthResponse(token, jwtService.expirationSeconds))
     }
}
