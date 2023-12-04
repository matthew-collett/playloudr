package com.playloudr.app.model.entity

data class SpotifyResponse(
  val tracks: TracksResponse
)

data class TracksResponse(
  val items: List<Track>
)

data class Track(
  val id: String,
  val name: String
)
