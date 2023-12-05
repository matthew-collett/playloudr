package com.playloudr.app.view.screens.search

import com.playloudr.app.model.entity.UserEntity

sealed class SearchState {
  object Idle : SearchState()
  object Loading : SearchState()
  data class Loaded(val users: List<UserEntity>) : SearchState()
  data class NoResults(val reason: String) : SearchState()
  data class Error(val exception: Throwable) : SearchState()
}