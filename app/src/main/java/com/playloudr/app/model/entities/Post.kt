package com.playloudr.app.model.entities

import com.playloudr.app.model.enums.PostType

data class Post(
  val username: String,
  val timestamp: String,
  val title: String,
  val artist: String,
  val caption: String?,
  val imageUrl: String,
  val audioUrl: String?,
  val type: PostType
)