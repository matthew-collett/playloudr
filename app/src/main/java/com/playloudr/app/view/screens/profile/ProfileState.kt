package com.playloudr.app.view.screens.profile

import com.playloudr.app.model.entity.PostEntity
import com.playloudr.app.model.entity.ProfileInfoEntity
import com.playloudr.app.model.entity.UserEntity

sealed class ProfileState {
  object RefreshLoading : ProfileState()
  data class ProfileLoaded(
    val user: UserEntity,
    val posts: List<PostEntity>,
    val profileInfo: ProfileInfoEntity
  ) : ProfileState()
  data class Error(val exception: Throwable) : ProfileState()
}