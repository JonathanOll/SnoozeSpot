package iut.fauryollivier.snoozespot.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import iut.fauryollivier.snoozespot.api.generated.model.PostCommentDTO
import iut.fauryollivier.snoozespot.api.generated.model.UserDTO
import iut.fauryollivier.snoozespot.api.generated.model.SpotAttributeDTO
import iut.fauryollivier.snoozespot.api.generated.model.SpotCommentDTO
import iut.fauryollivier.snoozespot.api.generated.model.StoredFileDTO

class RoomJsonConverters {

    private val gson = Gson()

    @TypeConverter
    fun fromUserDTO(value: UserDTO?): String? =
        value?.let { gson.toJson(it) }

    @TypeConverter
    fun toUserDTO(value: String?): UserDTO? =
        value?.let { gson.fromJson(it, UserDTO::class.java) }

    @TypeConverter
    fun fromStoredFileDTOList(list: List<StoredFileDTO>?): String? =
        list?.let { gson.toJson(it) }

    @TypeConverter
    fun toStoredFileDTOList(json: String?): List<StoredFileDTO>? {
        if (json == null) return null
        val type = object : TypeToken<List<StoredFileDTO>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromSpotAttributeDTOList(list: List<SpotAttributeDTO>?): String? =
        list?.let { gson.toJson(it) }

    @TypeConverter
    fun toSpotAttributeDTOList(json: String?): List<SpotAttributeDTO>? {
        if (json == null) return null
        val type = object : TypeToken<List<SpotAttributeDTO>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromSpotCommentDTOList(list: List<SpotCommentDTO>?): String? =
        list?.let { gson.toJson(it) }

    @TypeConverter
    fun toSpotCommentDTOList(json: String?): List<SpotCommentDTO>? {
        if (json == null) return null
        val type = object : TypeToken<List<SpotCommentDTO>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromPostCommentDTOList(list: List<PostCommentDTO>?): String? =
        list?.let { gson.toJson(it) }

    @TypeConverter
    fun toPostCommentDTOList(json: String?): List<PostCommentDTO>? {
        if (json == null) return null
        val type = object : TypeToken<List<PostCommentDTO>>() {}.type
        return gson.fromJson(json, type)
    }
}
