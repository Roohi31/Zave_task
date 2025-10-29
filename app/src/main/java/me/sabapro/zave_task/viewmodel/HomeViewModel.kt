package me.sabapro.zave_task.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import me.sabapro.zave_task.model.PlaceEntity
import me.sabapro.zave_task.model.PlaceResult
import me.sabapro.zave_task.model.RecentSearch
import me.sabapro.zave_task.repository.PlacesRepository
import me.sabapro.zave_task.service.AppDatabase
import me.sabapro.zave_task.util.RemoteConfigManager
import retrofit2.http.Query

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val _bannerMessage = MutableStateFlow("")
    val bannerMessage = _bannerMessage.asStateFlow()

    private val _featuredCategory  = MutableStateFlow("")
    val featuredCategory  = _featuredCategory .asStateFlow()

    private val _defaultRadius  = MutableStateFlow(0)
    val defaultRadius  = _defaultRadius .asStateFlow()

    private val apiKey = "AIzaSyDbaidtYqyDd5QAVHCNKIsAIjdkS0d487E"

    private val _places = mutableStateListOf<PlaceResult>()
    val places: List<PlaceResult> get() = _places

    //Room DAO
    private val dao = AppDatabase.getDatabase(application).recentSearchDao()
    private val placeDao = AppDatabase.getDatabase(application).placeDao()

    val recentSearches: StateFlow<List<RecentSearch>> =
        dao.getRecentSearches()
            .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())



    fun searchNearbyStores(query: String, context: Context) {
        val location = "12.9716,77.5946" // Bengaluru for demo
        val radius = 1500 // meters

        viewModelScope.launch {
            try {
                dao.insertSearch(RecentSearch(query = query))
                val response = PlacesRepository.searchNearby(location, radius, query, apiKey)
                if(response.status == "OK") {
                    _places.clear()
                    _places.addAll(response.results)
                    saveApiResultsToRoom(response.results)
                    Toast.makeText(context, "Found ${response.results.size} results!", Toast.LENGTH_SHORT).show()
                    Log.d("Places", "Results: ${response.results}")
                } else {
                    Toast.makeText(context, "Error: ${response.status}", Toast.LENGTH_SHORT).show()
                }
            } catch (e : Exception) {
                Log.e("Places", "Error fetching places: ${e.message}")
                Toast.makeText(context, "Failed to fetch data", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private suspend fun saveApiResultsToRoom(results: List<PlaceResult>) {
        placeDao.clearAllPlaces() // clear old results (optional)
        placeDao.insertPlaces(
            results.map {
                val photoRef = it.photos?.firstOrNull()?.photo_reference
                val photoUrl = photoRef?.let { ref ->
                    "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=$ref&key=$apiKey"
                }
                Log.d("PhotoURl","place: ${it.name}, Photo URl: $photoUrl")
                PlaceEntity(
                    name = it.name,
                    vicinity = it.vicinity,
                    rating = it.rating,
                    photoUrl = photoUrl
                )
            }
        )
        Log.d("Room", "✅ Saved ${results.size} places to local DB")
    }

    init {
        viewModelScope.launch {
            val activated = RemoteConfigManager.init()
            val msg = RemoteConfigManager.getBannerMessage()
            val radius = RemoteConfigManager.getDefaultRadius()
            val category = RemoteConfigManager.getFeaturedCategory()
            Log.d("RemoteConfig","Activated: $activated, message: $msg")
            _bannerMessage.value = msg
            _featuredCategory.value = category
            _defaultRadius.value = radius
        }
    }
}