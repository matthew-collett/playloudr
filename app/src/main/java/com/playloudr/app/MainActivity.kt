package com.playloudr.app

import FeedHeader
import Navbar
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.playloudr.app.model.entities.posts
import com.playloudr.app.model.repository.PostRepository
import com.playloudr.app.view.screens.feed.FeedScreen
import com.playloudr.app.view.theme.PlayloudrTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      PlayloudrTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(),
          //color = MaterialTheme.colorScheme.background
        ) {
          FeedScreen(posts)
          Navbar()
        }
      }
    }
  }
}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//  Text(
//    text = "Hello $name!",
//    modifier = modifier
//  )
//}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//  PlayloudrTheme {
//    Column(
//      modifier = Modifier
//        .fillMaxSize()
//    ) {
//      FeedScreen(posts)
//      Navbar()
//    }
//  }
//}

@Composable
@Preview
fun GreetingPreview() {
  PlayloudrTheme {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)
    ) {
      FeedScreen(posts)
      Spacer(modifier = Modifier.weight(1f))
      Navbar()
    }
  }
}
