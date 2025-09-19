package iut.fauryollivier.snoozespot.models

data class Post(val user: User, val content: String, val medias: List<String> = emptyList(), val likesCount: Int = 0)
