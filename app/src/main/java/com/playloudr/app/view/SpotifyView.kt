package com.playloudr.app.view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.playloudr.app.viewmodel.SpotifyViewModel

@Composable
fun SpotifyView(viewModel: SpotifyViewModel) {
  // Observe the tracks state
  val tracks = viewModel.tracks.value

  // A simple list to display the tracks
  LazyColumn {
    items(tracks) { track ->
      Text("Track: ${track.name}, ID: ${track.id}")
    }
  }
}
