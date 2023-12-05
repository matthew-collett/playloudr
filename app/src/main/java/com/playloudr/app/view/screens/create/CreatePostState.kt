package com.playloudr.app.view.screens.create

import com.playloudr.app.model.client.spotify.SpotifyTrack

sealed class CreatePostScreenState {
  object Idle : CreatePostScreenState()
  object Loading : CreatePostScreenState()
  data class Success(val songs: List<SpotifyTrack>, val selectedSong: SpotifyTrack?) : CreatePostScreenState()
  data class Error(val exception: Exception) : CreatePostScreenState()
}