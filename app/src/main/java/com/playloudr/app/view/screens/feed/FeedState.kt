package com.playloudr.app.view.screens.feed

import com.playloudr.app.model.entities.PostEntity

sealed class FeedState {
  object RefreshLoading : FeedState()
  data class PostsLoaded(val posts: List<PostEntity>) : FeedState()
  data class NoPosts(val reason: String) : FeedState()
  data class Error(val exception: Throwable) : FeedState()
}