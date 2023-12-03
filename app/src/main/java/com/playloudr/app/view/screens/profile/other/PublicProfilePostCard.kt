package com.playloudr.app.view.screens.profile.other

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
fun PublicProfilePostCard(
  post:PostEntity,
  onClick: (PostEntity) -> Unit
) {
  Card(
    modifier = Modifier
      .padding(4.dp)
      .aspectRatio(1f)
      .clickable { onClick(post) },
    elevation = 4.dp
  ) {
    Image(
      painter = rememberImagePainter(data = post.imageUrl),
      contentDescription = "${post.username}'s post",
      contentScale = ContentScale.Crop
    )
  }
}