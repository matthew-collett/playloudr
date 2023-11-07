package com.playloudr.app.view.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.playloudr.app.model.entities.PostEntity

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ProfilePostCard(post:PostEntity) {
  Card(
    modifier = Modifier
      .padding(4.dp) // This adds padding around each card
      .aspectRatio(1f), // This makes the card square
    elevation = 4.dp // Optional: if you want to have elevation for the card
  ) {
    Image(
      painter = rememberImagePainter(data = post.imageUrl),
      contentDescription = "${post.username}'s post",
      contentScale = ContentScale.Crop
    )
  }
}