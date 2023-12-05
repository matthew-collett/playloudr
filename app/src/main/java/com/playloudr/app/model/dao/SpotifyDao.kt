package com.playloudr.app.model.dao

import com.playloudr.app.model.client.spotify.SpotifyTrack
import com.playloudr.app.model.client.spotify.SpotifyServiceClient

object SpotifyDao {
  private val client = SpotifyServiceClient.getSpotify()
  suspend fun query(query: String, type: String): List<SpotifyTrack> {
    val response = client.query(query = query, type = type)
    if (!response.isSuccessful) {
      throw IllegalArgumentException("Failed to fetch tracks: ${response.errorBody()?.string()}")
    }
    return response.body()!!.tracks.items
  }
}