package com.playloudr.app.view.screens.signin

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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.playloudr.app.R
import com.playloudr.app.view.screens.Screen
import com.playloudr.app.view.theme.PLLogoColor

@Composable
fun SignUpScreen(navController: NavController) {
  var nameText by remember { mutableStateOf("") }
  var username by remember { mutableStateOf("") }
  var email by remember { mutableStateOf("") }
  var password by remember { mutableStateOf("") }
  var confirmPassword by remember { mutableStateOf("") }
  var isPasswordVisible by remember { mutableStateOf(false) }

  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(Color.White) // This is a light blue color, you can adjust as needed.
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
      // Logo
      Image(
        painter = painterResource(id = R.drawable.ic_playloudr_logo),
        contentDescription = "PlayLoudr logo",
        modifier = Modifier
          .size(200.dp)
      )

      Spacer(modifier = Modifier.height(24.dp))

      // Title
      Text(
        text = "Hey there! Let's get you setup!",
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
      )

      Spacer(modifier = Modifier.height(24.dp))

      // Name Input
      OutlinedTextField(
        value = nameText,
        onValueChange = { nameText = it},
        label = { Text("Name:") }
      )

      Spacer(modifier = Modifier.height(16.dp))

      // Username Input
      OutlinedTextField(
        value = username,
        onValueChange = { username = it},
        label = { Text("Username:") }
      )

      Spacer(modifier = Modifier.height(16.dp))

      // Email Input
      OutlinedTextField(
        value = email,
        onValueChange = { email = it},
        label = { Text("Email:") }
      )

      Spacer(modifier = Modifier.height(16.dp))

      // Password Input
      OutlinedTextField(
        value = password,
        onValueChange = { password = it},
        label = { Text("Password:") },
        visualTransformation = PasswordVisualTransformation() // Hide the password
      )

      Spacer(modifier = Modifier.height(16.dp))

      // Confirm Password Input
      OutlinedTextField(
        value = confirmPassword,
        onValueChange = { confirmPassword = it},
        label = { Text("Confirm Password:") },
        visualTransformation = PasswordVisualTransformation() // Hide the password
      )

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
          onClick = { /* sign up action */ },
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

@Preview
@Composable
fun PreviewSignUpScreen() {
  SignUpScreen(mockNavController())
}
