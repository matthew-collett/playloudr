package com.playloudr.app.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playloudr.app.model.repository.PostRepository
import com.playloudr.app.model.repository.SpotifyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SpotifyViewModel() : ViewModel() {

  private val _songImageUrl = MutableStateFlow<String?>(null)
  val songImageUrl: StateFlow<String?> = _songImageUrl
  fun searchSong(songName: String) {
    viewModelScope.launch {
      // need to use Dispatchers.IO since it is network call
      withContext(Dispatchers.IO) {
        // First, get the access token
        val accessToken = SpotifyRepository.getSpotifyAccessToken()
        if (accessToken != null) {
          Log.d("SpotifyViewModel", "access token success in SpotifyViewModel")
          // If the token retrieval was successful, search for the song
          val imageUrl = SpotifyRepository.searchSong(songName)
          withContext(Dispatchers.Main) {
            _songImageUrl.value = imageUrl
          }
        } else {
          Log.e("SpotifyViewModel", "access token received is null in SpotifyViewModel")
          withContext(Dispatchers.Main) {
            _songImageUrl.value = null
          }
        }
      }
    }
  }

}