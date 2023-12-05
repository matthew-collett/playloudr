package com.playloudr.app.model.client.spotify

import com.playloudr.app.model.entity.SpotifyTrackEntity

data class SpotifyResponse(
  val tracks: List<SpotifyTrackEntity>
)