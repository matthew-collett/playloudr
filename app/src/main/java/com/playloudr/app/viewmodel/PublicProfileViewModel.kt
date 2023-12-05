package com.playloudr.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playloudr.app.model.entity.ProfileInfoEntity
import com.playloudr.app.model.repository.PostRepository
import com.playloudr.app.model.repository.UserRepository
import com.playloudr.app.service.SessionManager
import com.playloudr.app.view.screens.feed.FeedState
import com.playloudr.app.view.screens.profile.ProfileState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PublicProfileViewModel: ViewModel()  {
  private lateinit var username: String
  private val postRepository = PostRepository
  private val userRepository = UserRepository
  private val _profileState = MutableStateFlow<ProfileState>(ProfileState.RefreshLoading)
  val profileState: StateFlow<ProfileState> = _profileState
  private val _isFollowed = MutableStateFlow<Boolean?>(null)
  val isFollowed: StateFlow<Boolean?> = _isFollowed.asStateFlow()

  fun init(username: String) {
    if (!this::username.isInitialized) {
      this.username = username
      loadProfile()
      fetchFollowStatus()
    }
  }

  private fun loadProfile() {
    viewModelScope.launch {
      try {
        if (!::username.isInitialized) {
          throw UninitializedPropertyAccessException("Username has not been initialized")
        }

        val user = userRepository.getUserProfile(username)!!
        val profileInfo = ProfileInfoEntity(
          getNumUserPosts(),
          getNumUserFollowing(),
          getNumUserFollowers()
        )
        val userPosts = postRepository.getUserPosts(username)
        _profileState.value = ProfileState.ProfileLoaded(user, userPosts, profileInfo)
      } catch (e: Exception) {
        _profileState.value = ProfileState.Error(e)
      }
    }
  }

  fun refreshProfile() {
    _profileState.value = ProfileState.RefreshLoading
    loadProfile()
  }

  private fun fetchFollowStatus() {
    viewModelScope.launch {
      val currentUsername = SessionManager.getCurrentUser()!!
      _isFollowed.value = userRepository.isFollowing(currentUsername, username)
    }
  }

  fun toggleFollowStatus() {
    viewModelScope.launch {
      _isFollowed.value?.let { currentState ->
        val currentUsername = SessionManager.getCurrentUser()!!
        if (currentState) {
          userRepository.unfollowUser(currentUsername, username)
        } else {
          userRepository.followUser(currentUsername, username)
        }
        _isFollowed.value = !currentState
      }
    }
  }

  private suspend fun getNumUserFollowers(): Int {
    return userRepository.getUserFollowers(username).count()
  }

  private suspend fun getNumUserFollowing(): Int {
    return userRepository.getUserFollowing(username).count()
  }

  private suspend fun getNumUserPosts(): Int {
    return postRepository.getUserPosts(username).count()
  }
}