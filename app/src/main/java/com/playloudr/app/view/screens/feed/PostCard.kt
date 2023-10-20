package com.playloudr.app.view.screens.feed

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.playloudr.app.model.entities.Type
import com.playloudr.app.model.entities.posts
import com.playloudr.app.view.theme.PlayloudrTheme

@Composable
fun PostCard(
  username: String,
  timestamp: String,
  title: String,
  artist: String,
  caption: String?,
  imageUrl: String,
  audioUrl: String?,
  type: Type
) {
  // Create a card for each post
  Card {
    Column {
      Text(username,
        modifier = Modifier
          .padding(8.dp)
      )
      // This is a simplified stand-in for an image
      Image(
        painter =
        rememberImagePainter(imageUrl),
        contentDescription = "User Post Image",
        modifier = Modifier
          .fillMaxWidth()
          .height(200.dp)
      )
      Column(Modifier.padding(8.dp)) {
        Text(
          text = title,
          style = MaterialTheme.typography.displaySmall,
          modifier = Modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth(),
          //color = MaterialTheme.colorScheme.,
        )
        Text(
          text = artist,
          style = MaterialTheme.typography.bodyMedium,
          modifier = Modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth(),
          //color = MaterialTheme.colorScheme.,
        )
        Text(
          text = type.name,
          style = MaterialTheme.typography.bodyMedium,
          modifier = Modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth(),
          //color = MaterialTheme.colorScheme.,
        )
        if (caption != null) {
          Text(
            caption,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 4.dp)
          )
        }
      }
    }
  }
}

/**
 * username: String,
 *   timestamp: String,
 *   title: String,
 *   artist: String,
 *   caption: String?,
 *   imageUrl: String,
 *   audioUrl: String?,
 *   type: Type
 */
@Preview
@Composable
fun PostCardPreview() {
  PlayloudrTheme {
    PostCard(
      posts[0].username,
      posts[0].timestamp,
      posts[0].title,
      posts[0].artist,
      posts[0].caption,
      posts[0].imageUrl,
      posts[0].audioUrl,
      posts[0].type
    )
 }
}