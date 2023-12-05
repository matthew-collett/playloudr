package com.playloudr.app.view.screens.create

import com.playloudr.app.model.entities.PostEntity

sealed class CreatePostScreenState {
  object RefreshLoading : CreatePostScreenState()
  data class PostPosted(val posts: List<PostEntity>) : CreatePostScreenState()
  data class Error(val exception: Throwable) : CreatePostScreenState()

}