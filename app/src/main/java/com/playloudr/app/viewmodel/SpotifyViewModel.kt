package com.playloudr.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playloudr.app.model.dao.SpotifyDao
import com.playloudr.app.model.entity.TrackItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SpotifyViewModel() : ViewModel() {
  private val _spotifySearchState = MutableStateFlow<SpotifySearchState>(SpotifySearchLoading)
  val spotifySearchState: StateFlow<SpotifySearchState> = _spotifySearchState

  fun searchSpotifyTracks(query: String, type: String = "track") {
    viewModelScope.launch {
        _spotifySearchState.value = SpotifySearchLoading
        SpotifyDao().queryTest()

    }
  }
}

sealed class SpotifySearchState
object SpotifySearchLoading : SpotifySearchState()
data class SpotifySearchResultsLoaded(val items: List<TrackItem>) : SpotifySearchState()
data class SpotifySearchError(val message: String) : SpotifySearchState()
