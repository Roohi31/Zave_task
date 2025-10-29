package me.sabapro.zave_task.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "places_table")
data class PlaceEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val vicinity: String?,
    val rating: Double?,
    val photoUrl: String?
)


