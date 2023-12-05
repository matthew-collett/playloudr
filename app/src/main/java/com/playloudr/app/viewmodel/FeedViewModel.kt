package com.playloudr.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playloudr.app.model.repository.PostRepository
import com.playloudr.app.service.SessionManager
import com.playloudr.app.view.screens.feed.FeedState
import com.playloudr.app.view.screens.feed.FeedState.Error
import com.playloudr.app.view.screens.feed.FeedState.NoPosts
import com.playloudr.app.view.screens.feed.FeedState.PostsLoaded
import com.playloudr.app.view.screens.feed.FeedState.RefreshLoading
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FeedViewModel : ViewModel() {
  private val postRepository: PostRepository = PostRepository
  private val _feedState = MutableStateFlow<FeedState>(RefreshLoading)
  val feedState: StateFlow<FeedState> = _feedState

  init {
    loadFeedPosts()
  }

  private fun loadFeedPosts() {
    viewModelScope.launch {
      try {
        val feedPosts = postRepository.getFeedPosts(SessionManager.getCurrentUser()!!)
        if (feedPosts.isEmpty()) {
          _feedState.value = NoPosts("Your friends have no posts or you need to follow a friend!")
        } else {
          _feedState.value = PostsLoaded(feedPosts)
        }
      } catch (e: Exception) {
        _feedState.value = Error(e)
      }
    }
  }
}


