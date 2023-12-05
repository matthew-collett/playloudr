package com.playloudr.app.model.entity

import java.time.Instant

data class PostEntity(
  val username: String,
  val timestamp: Instant,
  val title: String,
  val artist: String,
  val caption: String?,
  val imageUrl: String
)
