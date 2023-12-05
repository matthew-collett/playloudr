package com.playloudr.app.view.screens.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.playloudr.app.R
import com.playloudr.app.view.screens.Screen
import com.playloudr.app.view.theme.PLLogoColor
import com.playloudr.app.viewmodel.SignInViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SignInScreenComposable(navController: NavController, viewModel: SignInViewModel, state: String?) {
  var username by remember { mutableStateOf("") }
  var password by remember { mutableStateOf("") }
  var isPasswordVisible by remember { mutableStateOf(false) }
  val keyboardController = LocalSoftwareKeyboardController.current
  Box(
  modifier = Modifier
    .fillMaxSize()
    .background(Color.White),
  contentAlignment = Alignment.Center
  ) {
    Column(
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Image(
        painter = painterResource(id = R.drawable.ic_playloudr_icon), // Replace with your image resource name
        contentDescription = "Logo",
        contentScale = ContentScale.Fit,
        modifier = Modifier
          .width(100.dp)
          .height(100.dp)
      )

      Spacer(modifier = Modifier.height(20.dp))

      Text(
        "Sign In",
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
      )

      Spacer(modifier = Modifier.height(16.dp))

      OutlinedTextField(
        value = username,
        onValueChange = { username = it },
        label = { Text("Username") },
        modifier = Modifier
          .fillMaxWidth(0.8f)
      )

      Spacer(modifier = Modifier.height(12.dp))

      OutlinedTextField(
        value = password,
        onValueChange = { password = it },
        label = { Text("Password") },
        modifier = Modifier
          .fillMaxWidth(0.8f),
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions.Default.copy(
          imeAction = if (isPasswordVisible) ImeAction.Done else ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
          onSearch = {
            viewModel.signIn(username, password)
            keyboardController?.hide()
          }
        ),
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
      if (state != null) {
        Text(
          text = state,
          color = Color.Red
        )
      }

      Spacer(modifier = Modifier.height(18.dp))

      Button(
        onClick = { viewModel.signIn(username, password) },
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(
          backgroundColor = PLLogoColor,
          contentColor = Color.White
        ),
        modifier = Modifier.height(48.dp)
          .fillMaxWidth(0.8f)
      ) {
        Text(
          text = "Sign In"
        )
      }
      Spacer(modifier = Modifier.height(12.dp))

      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
      ) {
        Text("Don't have an account?")
        TextButton(onClick = {
          navController.navigate(Screen.SignUp.route) {
            popUpTo(navController.graph.startDestinationId) {
              saveState = true
            }
            launchSingleTop = true
          }

        }) {
          Text(
            text = "Sign Up",
            color = PLLogoColor
          )
        }
      }
    }
  }
}