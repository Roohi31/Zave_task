package me.sabapro.zave_task.repository

import me.sabapro.zave_task.model.PlacesResponse
import me.sabapro.zave_task.service.RetrofitInstance

object PlacesRepository {
    suspend fun searchNearby(
        location: String,
        radius: Int,
        type: String,
        apiKey: String
    ): PlacesResponse {
        return RetrofitInstance.api.getNearbyPlaces(location, radius, type, apiKey)
    }
}