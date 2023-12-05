package com.playloudr.app.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.playloudr.app.model.entity.PostEntity
import com.playloudr.app.util.DateTimeUtils.formatTimestamp
import com.playloudr.app.view.screens.Screen

@OptIn(ExperimentalCoilApi::class)
@Composable
fun PostCard(post: PostEntity, navController: NavController, isPublic: Boolean) {
  Column(
    modifier = Modifier
      .padding(vertical = 8.dp)
      .fillMaxWidth()
  ) {
    Row(
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.Bottom,
      modifier = Modifier
        .padding(end = 8.dp)
        .fillMaxWidth()
    ) {
      Row {
        IconButton(onClick = { /* Handle click */ }) {
          Icon(Icons.Default.PlayArrow, contentDescription = "Play Music")
        }
        Column {
          Text(
            text = post.title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
          )
          Text(
            text = post.artist,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
          )
        }
      }
    }

    Image(
      painter = rememberImagePainter(data = post.imageUrl),
      contentDescription = "Post Image",
      contentScale = ContentScale.Crop,
      modifier = Modifier
        .fillMaxWidth()
        .aspectRatio(1f)
    )

    Column(
      modifier = Modifier
        .padding(8.dp)
    ) {
      Row {
        Text(
          text = post.username,
          fontWeight = FontWeight.Bold,
          modifier = Modifier.clickable {
            navController.navigate(
              if (isPublic) Screen.PublicProfile.createRoute(post.username) else Screen.MyProfile.route
            )
          }
        )
        Spacer(
          modifier = Modifier
            .width(4.dp)
        )
        post.caption?.let { Text(text = it) }
      }
      Text(
        text = formatTimestamp(post.timestamp),
        fontSize = 12.sp,
        color = Color.Gray
      )
    }
  }
}
