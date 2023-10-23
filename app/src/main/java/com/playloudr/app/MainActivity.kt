package com.playloudr.app

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
  Scaffold(
    topBar = {
      FeedTopBar()
    },
    bottomBar = {
      BottomNavigationBar(navController)
    }
  ) { paddingValues ->
    NavigationHost(navController = navController, modifier = Modifier.padding(paddingValues))
  }
}



@Preview
@Composable
fun MainActivityPreview() {
  MainApplicationView()
}

