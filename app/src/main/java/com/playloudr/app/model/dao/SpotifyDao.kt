package com.playloudr.app.model.dao

import com.playloudr.app.model.client.spotify.SpotifyServiceClient
import com.playloudr.app.model.entity.SpotifyTrackEntity

class SpotifyDao {
  private val client = SpotifyServiceClient.getSpotify()
  suspend fun query(query: String, type: String): List<SpotifyTrackEntity> {
    val response = client.query(query = query, type = type)
    if (!response.isSuccessful) {
      throw IllegalArgumentException("Failed to fetch tracks: ${response.errorBody()?.string()}")
    }
    return response.body()!!.tracks
  }
}