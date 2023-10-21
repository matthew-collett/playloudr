package com.playloudr.app.model.entities

data class UserEntity(
  val username: String,
  val profilePictureUrl: String,
  val displayName: String?,
  val bio: String?,
  val email: String,
  val password: String
)