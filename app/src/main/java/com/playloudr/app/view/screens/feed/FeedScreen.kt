package com.playloudr.app.view.screens.feed

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.playloudr.app.model.entities.PostEntity
import com.playloudr.app.view.components.ScrollAwareLazyColumn

@Composable
fun FeedScreen(postList: List<PostEntity>, onScrollDown: () -> Unit, onScrollUp: () -> Unit) {
  ScrollAwareLazyColumn(
    modifier = Modifier.fillMaxWidth(),
    scrollUp = onScrollUp,
    scrollDown = onScrollDown
  ) {
    items(postList) { post ->
      PostCard(post)
    }
  }
}

