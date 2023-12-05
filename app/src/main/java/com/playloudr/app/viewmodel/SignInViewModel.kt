package com.playloudr.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playloudr.app.model.repository.UserRepository
import com.playloudr.app.view.screens.signin.SignInState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SignInViewModel : ViewModel() {
  private val userRepository = UserRepository
  private val _signInState = MutableStateFlow<SignInState>(SignInState.Idle)
  val signInState: StateFlow<SignInState> = _signInState

  fun signIn(username: String, password: String) {
    viewModelScope.launch {
      _signInState.value = SignInState.Loading
      try {
        if (userRepository.signIn(username, password)) {
          _signInState.value = SignInState.Success
        } else {
          _signInState.value = SignInState.Failure("Invalid username or password")
        }
      } catch (e: Exception) {
        _signInState.value = SignInState.Error(e)
      }
    }
  }
}