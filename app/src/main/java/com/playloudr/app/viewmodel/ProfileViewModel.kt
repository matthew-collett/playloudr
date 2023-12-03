package com.playloudr.app.viewmodel

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

  fun getUserPosts(): List<PostEntity> {
    return posts.filter {postEntity ->
      _username.value?.let { username ->
        postEntity.username.lowercase().contains(username.lowercase())
      } ?: false
    }
  }

  fun getUserProfilePicture(){}

  fun getUserFollowers(){}

  fun getUserFollowing(){}

  fun getNumUserPosts(){}

}