package com.playloudr.app.viewmodel

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playloudr.app.model.entities.PostEntity
import com.playloudr.app.model.entities.posts
import com.playloudr.app.model.entities.users
import com.playloudr.app.model.repository.PostRepository
import com.playloudr.app.view.screens.feed.FeedState
import com.playloudr.app.view.screens.profile.ProfileState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel: ViewModel()  {
  private val _username = MutableLiveData<String>()
  private val _profileState = MutableStateFlow<ProfileState>(ProfileState.RefreshLoading)
  val profileState: StateFlow<ProfileState> = _profileState
  private val postRepository = PostRepository


  fun setUsername(username:String) {
    _username.value = username
    getUserPosts(username)
  }

  fun setProfilePicture(uri: Uri) {
    users.forEach{user ->
      if (user.username == _username.value) {
        user.profilePictureUrl = uri.toString()
      }
    }
  }

  private fun getUserPosts(username: String) {
    viewModelScope.launch {
      _profileState.value = ProfileState.RefreshLoading
      delay(1000)
      try {
        val userPosts = postRepository.tempGetUserPosts(username) // Replace with actual repository call
        if (userPosts.isEmpty()) {
          _profileState.value = ProfileState.NoPosts("No posts available")
        } else {
          _profileState.value = ProfileState.PostsLoaded(userPosts)
        }
      } catch (e: Exception) {
        _profileState.value = ProfileState.Error(e)
      }
    }
  }

  fun tempGetUserPosts(): List<PostEntity> {
    return posts.filter {postEntity ->
      _username.value?.let { username ->
        postEntity.username.lowercase().contains(username.lowercase())
      } ?: false
    }
  }

  fun getUserProfilePicture(){}

  fun getUserUsername(){}
  fun getUserFollowers(){}

  fun getUserFollowing(){}

  fun getNumUserPosts(){}

}