package iut.fauryollivier.snoozespot.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import org.openapitools.client.models.room.SpotDTORoomModel

@Dao
interface SpotDao {
    @Query("SELECT * FROM SpotDTO")
    suspend fun getAll(): List<SpotDTORoomModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(spot: SpotDTORoomModel)

    @Delete
    suspend fun delete(spot: SpotDTORoomModel)

    @Query("SELECT EXISTS(SELECT 1 FROM SpotDTO WHERE id = :spotId)")
    suspend fun exist(spotId: Int): Boolean

    @Query("DELETE FROM SpotDTO WHERE id = :spotId")
    suspend fun deleteById(spotId: Int)
}