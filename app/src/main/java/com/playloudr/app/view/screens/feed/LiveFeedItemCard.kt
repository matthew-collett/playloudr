package com.playloudr.app.view.screens.feed

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.playloudr.app.model.entities.PostEntity

@Composable
fun LiveFeedItemCard(item: PostEntity) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .padding(16.dp)
  ) {
    Text(text = item.imageUrl, modifier = Modifier.padding(16.dp))
  }

}