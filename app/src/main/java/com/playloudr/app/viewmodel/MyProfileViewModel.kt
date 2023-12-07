package com.playloudr.app.viewmodel

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playloudr.app.model.entity.ProfileInfoEntity
import com.playloudr.app.model.repository.PostRepository
import com.playloudr.app.model.repository.UserRepository
import com.playloudr.app.service.SessionManager
import com.playloudr.app.view.screens.profile.ProfileState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.File

class MyProfileViewModel: ViewModel()  {
  private var username: String = SessionManager.getCurrentUser()!!
  private val postRepository = PostRepository
  private val userRepository = UserRepository
  private val _profileState = MutableStateFlow<ProfileState>(ProfileState.RefreshLoading)
  val profileState: StateFlow<ProfileState> = _profileState

  init {
    loadProfile()
  }

  private fun loadProfile() {
    viewModelScope.launch {
      try {
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

  private suspend fun getNumUserFollowers(): Int {
    return userRepository.getUserFollowers(username).count()
  }

  private suspend fun getNumUserFollowing(): Int {
    return userRepository.getUserFollowing(username).count()
  }

  private suspend fun getNumUserPosts(): Int {
    return postRepository.getUserPosts(username).count()
  }

  suspend fun updateProfilePicture(file: File?) {
    userRepository.updateProfilePicture(username, file)
  }

  fun logout() {
    SessionManager.logoutUser()
  }
}