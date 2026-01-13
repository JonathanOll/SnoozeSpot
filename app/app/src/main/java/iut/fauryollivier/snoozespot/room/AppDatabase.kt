package iut.fauryollivier.snoozespot.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import iut.fauryollivier.snoozespot.room.dao.SpotDao
import org.openapitools.client.models.room.SpotDTORoomModel

@Database(entities = [SpotDTORoomModel::class], version = 1)
@TypeConverters(RoomJsonConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun SpotDao(): SpotDao
}

object DatabaseBuilder {
    private var instance: AppDatabase? = null

    fun init(context: Context): AppDatabase {
        instance = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        )
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