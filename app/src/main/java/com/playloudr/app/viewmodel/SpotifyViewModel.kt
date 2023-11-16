package com.playloudr.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playloudr.app.model.repository.PostRepository
import com.playloudr.app.model.repository.SpotifyRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SpotifyViewModel() : ViewModel() {

  private val _songImageUrl = MutableStateFlow<String?>(null)
  val songImageUrl: StateFlow<String?> = _songImageUrl
  fun searchSong(songName: String) {
    viewModelScope.launch {
      // First, get the access token
      val accessToken = SpotifyRepository.getSpotifyAccessToken()
      if (accessToken != null) {
        // If the token retrieval was successful, search for the song
        val imageUrl = SpotifyRepository.searchSong(songName)
        _songImageUrl.value = imageUrl
      } else {
        // Handle the error (e.g., by updating some error state in the ViewModel)
        _songImageUrl.value = null
      }
    }
  }

  fun getAccessToken() {

  }
}