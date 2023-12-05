package com.playloudr.app.view.screens.profile

import com.playloudr.app.model.entities.PostEntity

sealed class ProfileState {
  object RefreshLoading : ProfileState()
  data class PostsLoaded(val posts: List<PostEntity>) : ProfileState()
  data class NoPosts(val reason: String) : ProfileState()
  data class Error(val exception: Throwable) : ProfileState()
}