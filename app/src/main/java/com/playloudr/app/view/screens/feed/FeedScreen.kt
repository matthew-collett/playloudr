package com.playloudr.app.view.screens.feed

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.playloudr.app.model.entities.PostEntity

@Composable
fun FeedScren(viewModel: /** LiveFeedViewModel need to make one*/) {
  val liveFeedItems = viewModel.getLiveFeedItems()

  LazyColumn(
    modifier = Modifier
      .fillMaxSize()
  ) {
    items(liveFeedItems) { item ->
      LiveFeedItemCard(item)
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

