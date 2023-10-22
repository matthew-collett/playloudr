package com.playloudr.app.model.enums

enum class PostType(val type: String) {
  SINGLE("Single"),
  PLAYLIST("Playlist"),
  ALBUM("Album"),
  EP("EP"),
  UNKNOWN("Unknown");

  companion object {
    fun fromString(type: String): PostType {
      values().forEach {
        if (it.type.equals(type, ignoreCase = true)) {
          return it
        }
      }
      return UNKNOWN
    }
  }
}