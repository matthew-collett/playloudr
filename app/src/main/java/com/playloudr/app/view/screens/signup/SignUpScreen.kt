package com.playloudr.app.view.screens.signup

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.playloudr.app.view.components.LoadingIndicator
import com.playloudr.app.view.screens.Screen
import com.playloudr.app.view.screens.signin.SignInScreenComposable
import com.playloudr.app.viewmodel.SignUpViewModel

@Composable
fun SignUpScreen(viewModel: SignUpViewModel, navController: NavController) {
  val context = LocalContext.current
  when (val signUpState = viewModel.signUpState.collectAsState().value) {
    is SignUpState.Idle -> SignUpScreenComposable(navController, viewModel, state = null)
    is SignUpState.Loading -> LoadingIndicator()
    is SignUpState.Success -> {
      LaunchedEffect(signUpState) {
        Toast.makeText(context, "Account created successfully, navigating to sign in page!", Toast.LENGTH_SHORT).show()
        navController.navigate(Screen.SignIn.route)
      }
    }
    is SignUpState.Failure -> {
      SignUpScreenComposable(
        navController = navController,
        viewModel = viewModel,
        state = signUpState.message
      )
    }
    is SignUpState.Error -> signUpState.exception.message?.let { SignUpScreenComposable(navController = navController, viewModel, state = it) }
  }

}