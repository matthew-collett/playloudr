package com.playloudr.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playloudr.app.model.entities.PostEntity
import com.playloudr.app.model.repository.PostRepository
import com.playloudr.app.view.screens.feed.FeedState
import com.playloudr.app.view.screens.feed.FeedState.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FeedViewModel(private val postRepository: PostRepository) : ViewModel() {
  private val _feedState = MutableStateFlow<FeedState>(RefreshLoading)
  val feedState: StateFlow<FeedState> = _feedState

  init {
    loadFeedPosts("matthew.collett")
  }

  private fun loadFeedPosts(username: String) {
    viewModelScope.launch {
      try {
        _feedState.value = RefreshLoading
        val feedPosts = postRepository.getFeedPosts(username)
        if (feedPosts.isEmpty()) {
          _feedState.value = NoPosts("No posts available")
        } else {
          _feedState.value = PostsLoaded(feedPosts)
        }
      } catch (e: Exception) {
        _feedState.value = Error(e)
      }
    }
  }
}


