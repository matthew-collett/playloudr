package com.playloudr.app.viewmodel

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.playloudr.app.model.entities.PostEntity
import com.playloudr.app.model.entities.posts
import com.playloudr.app.model.entities.users

class ProfileViewModel(): ViewModel()  {
  private val _username = MutableLiveData<String>()

  fun setUsername(username:String) {
    _username.value = username
  }

  fun setProfilePicture(uri: Uri) {
    users.forEach{user ->
      if (user.username == _username.value) {
        user.profilePictureUrl = uri.toString()
      }
    }
  }

  fun getUserPosts(): List<PostEntity> {
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