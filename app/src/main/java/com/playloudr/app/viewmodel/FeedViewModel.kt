package com.playloudr.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playloudr.app.model.entities.PostEntity
import com.playloudr.app.model.repository.PostRepository
import com.playloudr.app.view.ViewState
import com.playloudr.app.view.ViewState.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class FeedViewModel(private val postRepository: PostRepository) : ViewModel() {
  private val _feedState = MutableStateFlow<ViewState<List<PostEntity>>>(Loading)
  val feedState: StateFlow<ViewState<List<PostEntity>>> = _feedState

  init {
    loadFeedPosts("matthew.collett")
  }

  private fun loadFeedPosts(username: String) {
    viewModelScope.launch {
      try {
        _feedState.value = Loading
        val feedPosts = postRepository.getFeedPosts(username)
        _feedState.value = Success(feedPosts)
      } catch (e: Exception) {
        _feedState.value = Error(e)
      }
    }
  }
}

