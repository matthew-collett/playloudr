package com.playloudr.app.data.model

import com.playloudr.app.data.model.enums.PostType

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