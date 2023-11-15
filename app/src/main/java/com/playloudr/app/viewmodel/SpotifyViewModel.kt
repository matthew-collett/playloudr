package com.playloudr.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SpotifyViewModel : ViewModel() {

  private val _songImageUrl = MutableStateFlow<String?>(null)
  val songImageUrl: StateFlow<String?> = _songImageUrl

//  fun searchSong(songName: String) {
//    viewModelScope.launch {
//      val imageUrl = SpotifyApi.searchSong(songName)
//      _songImageUrl.value = imageUrl
//    }
//  }
}