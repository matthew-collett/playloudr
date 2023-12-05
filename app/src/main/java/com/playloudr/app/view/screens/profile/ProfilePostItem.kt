package com.playloudr.app.view.screens.profile

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
import com.playloudr.app.model.entity.PostEntity

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ProfilePostItem(
  post: PostEntity,
  onClick: (PostEntity) -> Unit
) {
  Image(
    painter = rememberImagePainter(data = post.imageUrl),
    contentDescription = "${post.username}'s post",
    contentScale = ContentScale.Crop,
    modifier = Modifier
      .aspectRatio(1f)
      .padding(1.dp)
      .clickable { onClick(post) }
  )
}