package com.playloudr.app.models

data class Post(
  val username: String,
  val timestamp: String,
  val caption: String?,
  val imageUrl: String,
  val audioUrl: String?
)