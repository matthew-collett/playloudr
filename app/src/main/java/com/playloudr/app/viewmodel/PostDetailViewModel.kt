package com.playloudr.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playloudr.app.model.repository.PostRepository
import com.playloudr.app.util.DateTimeUtils
import com.playloudr.app.view.screens.postdetail.PostDetailState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PostDetailViewModel: ViewModel()  {
  private lateinit var id: String
  private val postRepository = PostRepository
  private val _postDetailState = MutableStateFlow<PostDetailState>(PostDetailState.RefreshLoading)
  val postDetailState: StateFlow<PostDetailState> = _postDetailState


  fun init(id: String) {
    if (!this::id.isInitialized) {
      this.id = id
      loadPostDetail()
    }
  }

  private fun loadPostDetail() {
    viewModelScope.launch {
      try {
        if (!::id.isInitialized) {
          throw UninitializedPropertyAccessException("Post ID has not been initialized")
        }
        val (username, timestamp) = id.split("/")
        val post = postRepository.getUserPost(username, DateTimeUtils.resolveTimestamp(timestamp))!!
        _postDetailState.value = PostDetailState.PostLoaded(post)
      } catch (e: Exception) {
        _postDetailState.value = PostDetailState.Error(e)
      }
    }
  }
}