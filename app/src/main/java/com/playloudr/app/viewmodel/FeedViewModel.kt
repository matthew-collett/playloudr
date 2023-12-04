package com.playloudr.app.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playloudr.app.model.entities.UserEntity
import com.playloudr.app.model.repository.PostRepository
import com.playloudr.app.model.repository.UserRepository
import com.playloudr.app.view.screens.feed.FeedState
import com.playloudr.app.view.screens.feed.FeedState.Error
import com.playloudr.app.view.screens.feed.FeedState.NoPosts
import com.playloudr.app.view.screens.feed.FeedState.PostsLoaded
import com.playloudr.app.view.screens.feed.FeedState.RefreshLoading
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FeedViewModel(private val postRepository: PostRepository) : ViewModel() {
  private val _feedState = MutableStateFlow<FeedState>(RefreshLoading)
  val feedState: StateFlow<FeedState> = _feedState

  private val _showTopBar = MutableStateFlow(true)
  val showTopBar: StateFlow<Boolean> = _showTopBar

  private val _showSearchBar = MutableStateFlow(false)
  val showSearchBar: StateFlow<Boolean> = _showSearchBar

  private val _userSearchResults = MutableStateFlow<List<UserEntity>>(emptyList())
  val userSearchResults: StateFlow<List<UserEntity>> = _userSearchResults
  var hasSearched = mutableStateOf(false)


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

  fun onSearchIconClicked() {
    _showSearchBar.value = !_showSearchBar.value
    //optional reset
    //_showTopBar.value = !_showSearchBar.value
  }

  fun onUserSearchQuery(query: String) {
    if (query.isNotEmpty()) {
      viewModelScope.launch {
        val results = UserRepository.tempGetUsers(query)
        _userSearchResults.value = results
      }
    } else {
      _userSearchResults.value = emptyList()
    }
    hasSearched.value = true
  }

  fun clearSearch() {
    _userSearchResults.value = emptyList()
    hasSearched.value = false
  }

  fun onScrollUp() {
    _showTopBar.value = true
  }

  fun onScrollDown() {
    _showTopBar.value = false
  }
}


