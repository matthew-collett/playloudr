package com.playloudr.app.view.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.playloudr.app.view.ViewState.*
import com.playloudr.app.viewmodel.FeedViewModel

@Composable
fun FeedScreen(viewModel: FeedViewModel) {
  val feedState by viewModel.feedState.collectAsState()

  when (feedState) {
    is Loading -> {
      Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator() // Loading spinner
      }
    }

    is Success -> {
      val posts = (feedState as Success).data
      LazyColumn {
        items(items = posts) { post ->
          Text(text = post.title)
        }
      }
    }

    is Error -> {
      Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Error occurred: ${(feedState as Error).exception.message}")
      }
    }
  }
}
