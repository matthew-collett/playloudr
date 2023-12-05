package com.playloudr.app.model.client.spotify

data class SpotifyResponse(
  val tracks: SpotifyTracksResponse
)

data class SpotifyTracksResponse(
  val items: List<SpotifyTrack>
)

data class SpotifyTrack(
  val id: String,
  val name: String,
  val artists: List<SpotifyArtist>,
  val album: SpotifyAlbum
)

data class SpotifyArtist(
  val name: String
)

data class SpotifyAlbum(
  val images: List<SpotifyImage>
)

data class SpotifyImage(
  val url: String
)
