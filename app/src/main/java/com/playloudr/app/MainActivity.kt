package com.playloudr.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.playloudr.app.view.screens.Screen
import com.playloudr.app.view.screens.feed.FeedScreen
import com.playloudr.app.view.screens.profile.ProfileScreen
import com.playloudr.app.view.theme.PlayloudrTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      PlayloudrTheme {
        AppNavigation()
      }
    }
  }

  @Composable
  fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
      navController = navController,
      startDestination = Screen.Feed.route
    ) {

      composable(Screen.Feed.route) {
        FeedScreen()
      }

      composable(Screen.Profile.route) {
        ProfileScreen()
      }
    }
  }
}