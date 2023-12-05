package com.playloudr.app.viewmodel

import androidx.lifecycle.ViewModel
import com.playloudr.app.view.screens.profile.ProfileState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CreatePostViewModel : ViewModel() {
  private val _createPostState = MutableStateFlow<ProfileState>(ProfileState.RefreshLoading)
  val profileState: StateFlow<ProfileState> = _createPostState
}