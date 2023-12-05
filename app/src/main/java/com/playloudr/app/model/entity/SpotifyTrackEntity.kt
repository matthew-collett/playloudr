package com.playloudr.app.model.entity

data class SpotifyTrackEntity(
  val title: String,
  val artist: SpotifyArtistEntity,
  val image: SpotifyImageEntity
)