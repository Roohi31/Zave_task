package me.sabapro.zave_task.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import me.sabapro.zave_task.model.RecentSearch

@Dao
interface RecentSearchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearch(search: RecentSearch)

    @Query("SELECT * FROM recent_searches ORDER BY timestamp DESC LIMIT 3")
    fun getRecentSearches(): Flow<List<RecentSearch>>
}