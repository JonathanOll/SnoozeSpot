package iut.fauryollivier.snoozespot.api.auth

object Password {
    fun hash(plain: String): String = org.mindrot.jbcrypt.BCrypt.hashpw(plain, org.mindrot.jbcrypt.BCrypt.gensalt(12))
    fun verify(plain: String, hash: String): Boolean = org.mindrot.jbcrypt.BCrypt.checkpw(plain, hash)
}