package com.playloudr.app.view.screens.signup

sealed class SignUpState {
  object Idle: SignUpState()
  object Loading : SignUpState()
  object Success : SignUpState()
  data class Failure(val message: String) : SignUpState()
  data class Error(val exception: Throwable) : SignUpState()
}