package com.playloudr.app.model.dao

import com.playloudr.app.model.client.spotify.SpotifyClient

class SpotifyDao : AbstractDao<SpotifyClient>(SpotifyClient::class) {
  suspend fun queryTest() {
    try {
      val response = getClient().query(query = "Beatles", type = "track")
      if (response.isSuccessful && response.body() != null) {
        response.body()!!.tracks.items.forEach { track ->
          println("Track: ${track.name}, ID: ${track.id}")
        }
      } else {
        println("Failed to fetch tracks: ${response.errorBody()?.string()}")
      }
    } catch (e: Exception) {
      println("Error making search request: ${e.message}")
    }
  }
}