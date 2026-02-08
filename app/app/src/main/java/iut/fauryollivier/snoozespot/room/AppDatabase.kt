package iut.fauryollivier.snoozespot.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import iut.fauryollivier.snoozespot.api.generated.model.PostDTO
import iut.fauryollivier.snoozespot.room.dao.PostDao
import iut.fauryollivier.snoozespot.room.dao.SpotDao
import iut.fauryollivier.snoozespot.room.overridemodels.OverridePostDTORoomModel
import iut.fauryollivier.snoozespot.room.overridemodels.OverrideSpotDTORoomModel

@Database(entities = [OverrideSpotDTORoomModel::class, OverridePostDTORoomModel::class], version = 2)
@TypeConverters(RoomJsonConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun SpotDao(): SpotDao
    abstract fun PostDao(): PostDao

    suspend fun savePosts(posts: List<PostDTO>) {

        val toRemove = PostDao().count() - (20 - posts.count())
        PostDao().deleteLastN(toRemove)

        posts.forEach { dto ->
            PostDao().insert(OverridePostDTORoomModel(
                dto.id,
                dto.id,
                dto.user,
                dto.content,
                dto.likeCount,
                dto.likedByUser,
                dto.createdAt,
                comments = dto.comments,
                pictures = dto.pictures
            ))
        }
    }
}

object DatabaseBuilder {
    private var instance: AppDatabase? = null

    fun init(context: Context): AppDatabase {
        instance = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        )
            .fallbackToDestructiveMigration()
            .build()
        return instance!!
    }

    fun getInstance(): AppDatabase {
        if (instance == null) {
            throw IllegalStateException("Database not initialized")
        }
        return instance!!
    }
}