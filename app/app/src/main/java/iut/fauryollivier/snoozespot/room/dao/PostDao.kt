package iut.fauryollivier.snoozespot.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import iut.fauryollivier.snoozespot.room.overridemodels.OverridePostDTORoomModel

@Dao
interface PostDao {
    @Query("SELECT * FROM PostDTO")
    suspend fun getAll(): List<OverridePostDTORoomModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(post: OverridePostDTORoomModel)

    @Delete
    suspend fun delete(post: OverridePostDTORoomModel)

    @Query("SELECT EXISTS(SELECT 1 FROM PostDTO WHERE id = :postId)")
    suspend fun exist(postId: Int): Boolean

    @Query("DELETE FROM postdto WHERE id = :postId")
    suspend fun deleteById(postId: Int)

    @Query("SELECT COUNT(*) FROM PostDTO")
    suspend fun count(): Int

    @Query("""
        DELETE FROM PostDTO 
        WHERE id IN (
            SELECT id FROM PostDTO ORDER BY id DESC LIMIT :count
        )
    """)
    suspend fun deleteLastN(count: Int)

}