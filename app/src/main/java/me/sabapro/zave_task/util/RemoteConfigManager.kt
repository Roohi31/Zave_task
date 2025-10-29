package me.sabapro.zave_task.util

import com.google.firebase.Firebase
import com.google.firebase.remoteconfig.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings
import kotlinx.coroutines.tasks.await

object RemoteConfigManager
{
    private val remoteConfig = Firebase.remoteConfig
    suspend fun init(): Boolean {

        remoteConfig.setConfigSettingsAsync(
            remoteConfigSettings {
                minimumFetchIntervalInSeconds = 5
            }
        )

        val defaults = mapOf(
            "default_radius_km" to 20,
            "featured_category" to "Electronics",
            "banner_message" to "Explore deals near you!"
        )
        remoteConfig.setDefaultsAsync(defaults)
        return remoteConfig.fetchAndActivate().await()
    }

    fun getBannerMessage(): String = remoteConfig.getString("banner_message")
    fun getFeaturedCategory(): String = remoteConfig.getString("featured_category")
    fun getDefaultRadius(): Int = remoteConfig.getLong("default_radius_km").toInt()
}