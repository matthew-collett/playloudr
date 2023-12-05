package com.playloudr.app.view.screens.signin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.playloudr.app.view.components.LoadingIndicator
import com.playloudr.app.view.screens.Screen
import com.playloudr.app.viewmodel.SignInViewModel

@Composable
fun SignInScreen(viewModel: SignInViewModel, navController: NavController) {
  when (val signInState = viewModel.signInState.collectAsState().value) {
    is SignInState.Loading -> LoadingIndicator()
    is SignInState.Success -> {
      LaunchedEffect(signInState) {
        navController.navigate(Screen.Feed.route)
      }
    }
    is SignInState.Failure -> SignInScreenComposable(navController = navController, viewModel, state = signInState.message)
    is SignInState.Error -> signInState.exception.message?.let { SignInScreenComposable(navController = navController, viewModel, state = it) }
  }
}
