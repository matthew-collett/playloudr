package com.playloudr.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.playloudr.app.view.navigation.BottomNavigationBar
import com.playloudr.app.view.navigation.NavigationHost
import com.playloudr.app.view.screens.Screen
import com.playloudr.app.view.screens.signin.SignInScreen
import com.playloudr.app.view.screens.signin.SignUpScreen
import com.playloudr.app.view.theme.PlayloudrTheme
import com.playloudr.app.viewmodel.SpotifyViewModel

class MainActivity : ComponentActivity() {
  private val spotVM by viewModels<SpotifyViewModel>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      PlayloudrTheme {
        MainApplicationView()
        //SearchSong(viewModel = spotVM)
      }
    }
  }
}

@Composable
fun SignInApplicationView() {
  val navController = rememberNavController()
  NavHost(navController, startDestination = Screen.SignIn.route) {
    composable(Screen.SignIn.route) {
      SignInScreen(navController)
    }
    composable(Screen.SignUp.route) {
      SignUpScreen(navController)
    }
  }
}

@Composable
fun MainApplicationView() {
  val navController = rememberNavController()
  val navBackStackEntry by navController.currentBackStackEntryAsState()
  val currentRoute = navBackStackEntry?.destination?.route
  var showTopBar by remember { mutableStateOf(true) }
  val onScrollDown = { showTopBar = false }
  val onScrollUp = { showTopBar = true }
  Scaffold(
    bottomBar = {
      BottomNavigationBar(navController)
    }
  ) { paddingValues ->
    NavigationHost(
      navController = navController,
      modifier = Modifier.padding(paddingValues)
    )
  }
}


@Preview
@Composable
fun MainActivityPreview() {
  MainApplicationView()
}

@Preview
@Composable
fun SignInApplicationPreview() {
  SignInApplicationView()
}

