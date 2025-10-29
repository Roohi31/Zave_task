package me.sabapro.zave_task.service

import me.sabapro.zave_task.model.PlacesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PlacesApiService
{
    @GET("maps/api/place/nearbysearch/json")
    suspend fun getNearbyPlaces(
        @Query("location") location: String,
        @Query("radius") radius: Int,
        @Query("type") type: String,
        @Query("key") apiKey: String
    ): PlacesResponse
}