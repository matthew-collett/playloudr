package com.playloudr.app.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playloudr.app.model.entities.UserEntity
import com.playloudr.app.model.entities.posts
import com.playloudr.app.model.repository.PostRepository
import com.playloudr.app.model.repository.UserRepository
import com.playloudr.app.view.screens.feed.FeedState
import com.playloudr.app.view.screens.feed.FeedState.Error
import com.playloudr.app.view.screens.feed.FeedState.NoPosts
import com.playloudr.app.view.screens.feed.FeedState.PostsLoaded
import com.playloudr.app.view.screens.feed.FeedState.RefreshLoading
import com.playloudr.app.view.screens.feed.SearchState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FeedViewModel : ViewModel() {
  private val postRepository: PostRepository = PostRepository
  private val _feedState = MutableStateFlow<FeedState>(RefreshLoading)
  val feedState: StateFlow<FeedState> = _feedState

  private val _showTopBar = MutableStateFlow(true)
  val showTopBar: StateFlow<Boolean> = _showTopBar

  private val _showSearchBar = MutableStateFlow(false)
  val showSearchBar: StateFlow<Boolean> = _showSearchBar

  private val _userSearchResults = MutableStateFlow<List<UserEntity>>(emptyList())
  private val _searchState = MutableStateFlow<SearchState>(SearchState.Idle)
  val searchState: StateFlow<SearchState> = _searchState
  val userSearchResults: StateFlow<List<UserEntity>> = _userSearchResults
  var hasSearched = mutableStateOf(false)


  init {
    //loadFeedPosts("matthew.collett")
    tempLoadFeedPosts() // using this for now until Dynamo is hooked up
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

  private fun tempLoadFeedPosts() {
    viewModelScope.launch {
      _feedState.value = RefreshLoading
      delay(1000)
      try {
        val feedPosts = posts
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
        _searchState.value = SearchState.Loading
        delay(1000)
        try {
          val results = UserRepository.tempGetUsers(query)
          if (results.isEmpty()) {
            _searchState.value = SearchState.NoResults("No users found")
          } else {
            _searchState.value = SearchState.Loaded(results)
          }
        } catch (e: Exception) {
          _searchState.value = SearchState.Error(e)
        }
      }
    }
    hasSearched.value = true
  }

  fun clearSearch() {
    _userSearchResults.value = emptyList()
    hasSearched.value = false
  }

//  fun onScrollUp() {
//    _showTopBar.value = true
//  }
//
//  fun onScrollDown() {
//    _showTopBar.value = false
//  }
}


