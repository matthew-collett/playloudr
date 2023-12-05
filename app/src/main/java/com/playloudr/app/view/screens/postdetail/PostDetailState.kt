package com.playloudr.app.view.screens.postdetail

import com.playloudr.app.model.entity.PostEntity

sealed class PostDetailState {
  object RefreshLoading : PostDetailState()
  data class PostLoaded(val post: PostEntity) : PostDetailState()
  data class Error(val exception: Throwable) : PostDetailState()
}