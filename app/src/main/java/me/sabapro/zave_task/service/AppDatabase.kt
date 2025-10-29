package me.sabapro.zave_task.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import me.sabapro.zave_task.dao.PlaceDao
import me.sabapro.zave_task.dao.RecentSearchDao
import me.sabapro.zave_task.model.PlaceEntity
import me.sabapro.zave_task.model.RecentSearch

@Database(entities = [RecentSearch::class, PlaceEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun recentSearchDao(): RecentSearchDao

    abstract fun placeDao(): PlaceDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}