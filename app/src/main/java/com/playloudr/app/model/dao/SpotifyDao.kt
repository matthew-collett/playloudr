package com.playloudr.app.model.dao

import com.playloudr.app.model.client.spotify.SpotifyServiceClient
import com.playloudr.app.model.entity.Track

class SpotifyDao {
  private val client = SpotifyServiceClient.fuckkOff()
  suspend fun query(query: String, type: String): List<Track> {
    val response = client.query(query = query, type = type)
    if (!response.isSuccessful) {
      throw IllegalArgumentException("Failed to fetch tracks: ${response.errorBody()?.string()}")
    }
    return response.body()!!.tracks.items
  }
}