package com.playloudr.app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileViewModel(): ViewModel()  {
  private val _username = MutableLiveData<String>()

  fun setUsername(username:String) {
    _username.value = username
  }

}