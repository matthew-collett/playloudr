package com.playloudr.app

import Navbar
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.playloudr.app.model.entities.posts
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
          FeedScreen(posts, modifier = Modifier.fillMaxHeight())
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
    Column(modifier = Modifier.fillMaxSize()) {
      Box( modifier = Modifier.weight(1f).fillMaxWidth() ) {
        FeedScreen(postList = posts, modifier = Modifier)
      }
      Navbar()
    }

//    Column(
//      modifier = Modifier
//        .fillMaxSize()
//        .padding(8.dp)
//    ) {
//      FeedScreen(posts,
//        modifier = Modifier.weight(if)
//      )
//
//      Navbar()
//    }
  }
}
