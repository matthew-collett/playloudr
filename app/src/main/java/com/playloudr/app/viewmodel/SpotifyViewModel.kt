package com.playloudr.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import com.playloudr.app.model.dao.SpotifyDao
import com.playloudr.app.model.entity.Track

class SpotifyViewModel(private val spotifyDao: SpotifyDao) : ViewModel() {

  // State to hold track information
  private val _tracks = mutableStateOf<List<Track>>(emptyList())
  val tracks: State<List<Track>> = _tracks

  init {
    queryTracks("Beatles", "track")
  }
  private fun queryTracks(query: String, type: String) {
    viewModelScope.launch {
      val response = spotifyDao.query(query, type)
      _tracks.value = response
    }
  }

}
