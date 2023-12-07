package com.playloudr.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playloudr.app.model.repository.UserRepository
import com.playloudr.app.view.screens.signin.SignInState
import com.playloudr.app.view.screens.signup.SignUpState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {
  private val userRepository = UserRepository
  private val _signUpState = MutableStateFlow<SignUpState>(SignUpState.Idle)
  val signUpState: StateFlow<SignUpState> = _signUpState

  fun signUp(
    username: String,
    password: String,
    confirmPassword: String,
    email: String,
    bio: String?,
    displayName: String?
  ) {
    val usernameLower = username.lowercase()

    validatePassword(password, confirmPassword)?.let { return setSignUpFailure(it) }
    validateEmail(email)?.let { return setSignUpFailure(it) }


    viewModelScope.launch {
      _signUpState.value = SignUpState.Loading
      try {
        validateUsername(usernameLower)?.let { return@launch setSignUpFailure(it) }
        userRepository.signUp(usernameLower, password, email, bio, displayName)
        _signUpState.value = SignUpState.Success
      } catch (e: Exception) {
        _signUpState.value = SignUpState.Error(e)
      }
    }
  }

  private fun validatePassword(password: String, confirmPassword: String): String? {
    if (password != confirmPassword) {
      return "Passwords do not match"
    }

    if (password.length < 8) {
      return "Password must be at least 8 characters long"
    }

    if (!password.any { it.isUpperCase() }) {
      return "Password must contain an uppercase letter"
    }

    if (!password.any { it.isLowerCase() }) {
      return "Password must contain a lowercase letter"
    }

    if (!password.any { it.isDigit() }) {
      return "Password must contain a digit"
    }
    return null
  }

  private suspend fun validateUsername(username: String): String? {
    return if (userRepository.searchUsers(username).isNotEmpty()) "Username already taken" else null
  }

  private fun validateEmail(email: String): String? {
    val emailRegex = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}".toRegex()
    return if (!email.matches(emailRegex)) "Invalid Email" else null
  }

  private fun setSignUpFailure(errorMessage: String) {
    _signUpState.value = SignUpState.Failure(errorMessage)
  }

}