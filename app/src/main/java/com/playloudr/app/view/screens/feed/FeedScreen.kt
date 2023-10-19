package com.playloudr.app.view.screens.feed

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.playloudr.app.model.entities.PostEntity
import com.playloudr.app.viewmodel.FeedViewModel

@Composable
fun FeedScreen(viewModel: FeedViewModel = viewModel()) {
  // Observing the LiveData from the ViewModel
  val posts by viewModel.posts.observeAsState(initial = emptyList())

  // Basic column with posts
  LazyColumn {
    items(posts) { post ->
      PostCard(post)
    }
  }
}

@Composable
fun PostCard(post: PostEntity) {
  // Create a card for each post
  Card {
    Column {
      Text(text = post.username)
      // This is a simplified stand-in for an image
      Image(
        painter =
        rememberImagePainter(data = post.imageUrl),
        contentDescription = "User Post Image",
        modifier = Modifier.fillMaxWidth().height(200.dp)
      )
      post.caption?.let { Text(text = it) }
    }
  }
}


/**
 * class LiveFeedViewModel : ViewModel() {
 *     private val liveFeedItems = mutableStateListOf<PostEntity>()
 *
 *     fun addLiveFeedItem(item: LiveFeedItem) {
 *         liveFeedItems.add(item)
 *     }
 *
 *     fun getLiveFeedItems(): List<LiveFeedItem> {
 *         return liveFeedItems
 *     }
 * }
 *
 */

