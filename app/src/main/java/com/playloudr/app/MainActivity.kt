package com.playloudr.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.playloudr.app.view.navigation.BottomNavigationBar
import com.playloudr.app.view.navigation.NavigationHost
import com.playloudr.app.view.screens.feed.FeedTopBar
import com.playloudr.app.view.theme.PlayloudrTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      PlayloudrTheme {
        MainApplicationView()
      }
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
    topBar = {
      AnimatedVisibility(
        visible = showTopBar,
        enter = slideInVertically(initialOffsetY = { -it }),
        exit = slideOutVertically(targetOffsetY = { -it })
      ) {
        when (currentRoute) {
          "feed" -> FeedTopBar()
          else -> {}
        }
      }
    },
    bottomBar = {
      BottomNavigationBar(navController)
    }
  ) { paddingValues ->
    NavigationHost(
      navController = navController,
      modifier = Modifier.padding(paddingValues),
      onScrollDown = onScrollDown,
      onScrollUp = onScrollUp
    )
  }
}


@Preview
@Composable
fun MainActivityPreview() {
  MainApplicationView()
}

