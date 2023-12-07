package com.playloudr.app.view.screens.signup

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Alignment
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.navigation.NavController
import com.playloudr.app.R
import com.playloudr.app.view.screens.Screen
import com.playloudr.app.view.theme.PLLogoColor
import com.playloudr.app.viewmodel.SignUpViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SignUpScreenComposable(navController: NavController, viewModel: SignUpViewModel, state: String?) {
  var username by remember { mutableStateOf("") }
  var email by remember { mutableStateOf("") }
  var password by remember { mutableStateOf("") }
  var bio by remember { mutableStateOf("") }
  var displayName by remember { mutableStateOf("") }
  var confirmPassword by remember { mutableStateOf("") }
  var isPasswordVisible by remember { mutableStateOf(false) }
  var isConfirmPasswordVisible by remember { mutableStateOf(false) }
  val keyboardController = LocalSoftwareKeyboardController.current

  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(Color.White)
  ) {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp)
        .padding(bottom = 16.dp)
        .verticalScroll(rememberScrollState()),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center
    ) {
      Image(
        painter = painterResource(id = R.drawable.ic_playloudr_logo),
        contentDescription = "PlayLoudr logo",
        modifier = Modifier
          .size(200.dp)
      )
      Spacer(modifier = Modifier.height(24.dp))

      Text(
        text = "Hey there! Let's get you setup!",
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
      )
      Spacer(modifier = Modifier.height(16.dp))

      OutlinedTextField(
        value = username,
        onValueChange = { username = it},
        label = { Text("Username:") }
      )
      Spacer(modifier = Modifier.height(24.dp))

      OutlinedTextField(
        value = bio,
        onValueChange = { bio = it},
        label = { Text("bio:(optional)") }
      )
      Spacer(modifier = Modifier.height(24.dp))

      OutlinedTextField(
        value = displayName,
        onValueChange = { displayName = it},
        label = { Text("Display Name:(optional)") }
      )
      Spacer(modifier = Modifier.height(16.dp))

      OutlinedTextField(
        value = email,
        onValueChange = { email = it},
        label = { Text("Email:") }
      )
      Spacer(modifier = Modifier.height(16.dp))

      OutlinedTextField(
        value = password,
        onValueChange = { password = it},
        label = { Text("Password:") },
        visualTransformation =  if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
          IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
            Icon(
              painter = painterResource(
                id = if (isPasswordVisible) R.drawable.ic_playloudr_eye_slash else R.drawable.ic_playloudr_eye_slash_fill
              ),
              contentDescription = if (isPasswordVisible) "Hide password" else "Show password"
            )
          }
        }
      )
      Spacer(modifier = Modifier.height(16.dp))

      OutlinedTextField(
        value = confirmPassword,
        onValueChange = { confirmPassword = it},
        label = { Text("Confirm Password:") },
        visualTransformation =  if (isConfirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardActions = KeyboardActions(
          onSearch = {
            viewModel.signUp(username, password, confirmPassword, email, bio, displayName)
            keyboardController?.hide()
          }
        ),
        trailingIcon = {
          IconButton(onClick = { isConfirmPasswordVisible = !isConfirmPasswordVisible }) {
            Icon(
              painter = painterResource(
                id = if (isConfirmPasswordVisible) R.drawable.ic_playloudr_eye_slash else R.drawable.ic_playloudr_eye_slash_fill
              ),
              contentDescription = if (isConfirmPasswordVisible) "Hide password" else "Show password"
            )
          }
        }
      )
      if (state != null) {
        Text(
          text = state,
          color = Color.Red
        )
      }
      Spacer(modifier = Modifier.height(24.dp))

      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        Button(
          onClick = {
            navController.navigate(Screen.SignIn.route) {
              popUpTo(navController.graph.startDestinationId) {
                saveState = true
              }
              launchSingleTop = true
            }
          },
          shape = RoundedCornerShape(8.dp),
          colors = ButtonDefaults.buttonColors(
            backgroundColor = PLLogoColor,
            contentColor = Color.White
          )
        ) {
          Text("Back")
        }

        Button(
          onClick = { viewModel.signUp(username, password, confirmPassword, email, bio, displayName) },
          shape = RoundedCornerShape(8.dp),
          colors = ButtonDefaults.buttonColors(
            backgroundColor = PLLogoColor,
            contentColor = Color.White)
        ){
          Text("Create Account")
        }
      }
    }
  }
}