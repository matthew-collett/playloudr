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
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.playloudr.app.R
import com.playloudr.app.view.screens.Screen
import com.playloudr.app.view.theme.*

@Composable
fun SignInScreen(navController: NavController) {
  var text by remember { mutableStateOf("") }
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
        value = "",
        onValueChange = {},
        label = { Text("Username") },
        modifier = Modifier
          .fillMaxWidth(0.8f)
      )

      Spacer(modifier = Modifier.height(12.dp))

      OutlinedTextField(
        value = "",
        // TODO: fix the onValueChange to make text work
        onValueChange = {text = it},
        label = { Text("Password") },
        modifier = Modifier
          .fillMaxWidth(0.8f)
      )

      Spacer(modifier = Modifier.height(16.dp))

      Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(
          backgroundColor = PLLogoColor,
          contentColor = Color.White
        ),
        modifier = Modifier
          .fillMaxWidth(0.8f)
          .clip(RoundedCornerShape(16.dp)),
      ) {
        Text(
          text = "Sign In"
        )
      }

      Spacer(modifier = Modifier.height(12.dp))

      TextButton(onClick = {}) {
        Text(
          text = "Forgot Password?",
          style = MaterialTheme.typography.body1
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
            style = MaterialTheme.typography.body1
          )
        }
      }
    }
  }
}

// mock NavController just to run PreviewSignInScreen()
@Composable
fun mockNavController(): NavController {
  return rememberUpdatedState(NavController(LocalContext.current)).value
}


@Preview(showBackground = true)
@Composable
fun PreviewSignInScreen() {
  SignInScreen(navController = mockNavController())
}
