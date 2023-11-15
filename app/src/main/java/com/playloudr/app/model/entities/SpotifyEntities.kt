package com.playloudr.app.model.entities

import com.google.gson.annotations.SerializedName

data class SpotifySearchResponse(
  @SerializedName("albums") val albums: AlbumResults
)

data class AlbumResults(
  @SerializedName("items") val items: List<AlbumItem>
)

data class AlbumItem(
  @SerializedName("images") val images: List<Image>
)

data class Image(
  @SerializedName("url") val url: String,
  @SerializedName("height") val height: Int,
  @SerializedName("width") val width: Int
)

data class SpotifyAccessTokenResponse(
  @SerializedName("access_token") val accessToken: String,
  @SerializedName("token_type") val tokenType: String,
  @SerializedName("expires_in") val expiresIn: Int
)


