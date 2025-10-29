package me.sabapro.zave_task.model

import com.google.gson.annotations.SerializedName

data class PlacesResponse(
    val results: List<PlaceResult>,
    val status: String
)

data class PlaceResult(
    val name: String,
    val vicinity: String?,
    val rating: Double?,
    val place_id: String,
    val photos: List<Photo>?
)

data class Photo(
    @SerializedName("photo_reference") val photo_reference: String
)