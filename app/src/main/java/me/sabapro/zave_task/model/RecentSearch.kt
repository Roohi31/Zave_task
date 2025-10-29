package me.sabapro.zave_task.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "recent_searches")
data class RecentSearch(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val query: String,
    val timestamp: Long = System.currentTimeMillis()
)
