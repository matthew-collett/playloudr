package com.playloudr.app.model.entities

import java.time.Instant

data class PostEntity(
  val username: String,
  val timestamp: Instant,
  val title: String,
  val artist: String,
  val caption: String?,
  val imageUrl: String,
  val audioUrl: String?,
  val postType: PostType
)

enum class PostType(val type: String) {
  SINGLE("Single"),
  PLAYLIST("Playlist"),
  ALBUM("Album"),
  EP("EP"),
  UNKNOWN("Unknown");

  companion object {
    fun fromString(value: String): PostType {
      values().forEach {
        if (it.type == value) {
          return it
        }
      }
      return UNKNOWN
    }
  }
}
