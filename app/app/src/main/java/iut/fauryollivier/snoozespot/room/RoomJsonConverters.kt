package iut.fauryollivier.snoozespot.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import iut.fauryollivier.snoozespot.api.generated.model.UserDTO

class RoomJsonConverters {

    private val gson = Gson()

    @TypeConverter
    fun fromUserDTO(value: UserDTO?): String? =
        value?.let { gson.toJson(it) }

    @TypeConverter
    fun toUserDTO(value: String?): UserDTO? =
        value?.let { gson.fromJson(it, UserDTO::class.java) }
}