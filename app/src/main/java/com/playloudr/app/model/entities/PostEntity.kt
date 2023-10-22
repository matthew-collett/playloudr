package com.playloudr.app.model.entities

data class PostEntity(
  val username: String,
  val timestamp: String,
  val title: String,
  val artist: String,
  val caption: String?,
  val imageUrl: String,
  val audioUrl: String?,
  val type: Type,
  val profilePictureUrl: String
)

enum class Type {
  SONG, PLAYLIST, ALBUM, EP
}