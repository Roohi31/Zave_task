package me.sabapro.zave_task.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.sabapro.zave_task.model.PlaceEntity


@Dao
interface PlaceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlaces(places: List<PlaceEntity>)

    @Query("SELECT * FROM places_table")
    suspend fun getAllPlaces(): List<PlaceEntity>

    @Query("DELETE FROM places_table")
    suspend fun clearAllPlaces()
}