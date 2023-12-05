package com.playloudr.app.view.screens.signin

sealed class SignInState {
  object Loading : SignInState()
  object Success : SignInState()
  data class Failure(val message: String) : SignInState()
  data class Error(val exception: Throwable) : SignInState()
}