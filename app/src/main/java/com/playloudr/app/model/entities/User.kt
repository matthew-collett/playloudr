package com.playloudr.app.model.entities

data class User(
  val username: String,
  val displayName: String?,
  val bio: String?,
  val email: String,
  val password: String
)