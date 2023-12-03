package com.playloudr.app.model.entity

import com.playloudr.app.model.enum.PostType
import java.time.Instant

data class PostEntity(
  val username: String,
  val timestamp: Instant,
  val title: String,
  val artist: String,
  val caption: String?,
  val imageUrl: String,
  val audioUrl: String?,
  val postType: PostType,
  val profilePictureUrl: String,
  val postId: String
)
