package com.playloudr.app.model.entity

data class SearchResponse(
  val tracks: TracksResponse
)

data class TracksResponse(
  val items: List<TrackItem>
)

data class TrackItem(
  val id: String,
  val name: String
)
