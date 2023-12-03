package com.playloudr.app.view.screens.feed

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.playloudr.app.model.entities.UserEntity

// Composable to display on user search
@OptIn(ExperimentalCoilApi::class)
@Composable
fun UserRow(
  user: UserEntity,
  navController: NavController
) {
  Row(
    verticalAlignment = Alignment.CenterVertically,
    modifier = Modifier
      .fillMaxWidth()
      .padding(8.dp)
      .clickable{
        navController.navigate("profile/${user.username}")
      }
  ) {
    Image(
      painter = rememberImagePainter(
        data = user.profilePictureUrl,
        builder = {
          transformations(CircleCropTransformation())
        }
      ),
      contentDescription = "Profile picture of ${user.username}",
      modifier = Modifier
        .size(48.dp)
        .clip(CircleShape) // This will give the image a circular shape
        .border(1.dp, Color.Gray, CircleShape)
    )

    Spacer(Modifier.width(16.dp))

    Column {
      Text(
        text = user.username,
        fontWeight = FontWeight.Bold,
        overflow = TextOverflow.Ellipsis
      )

      user.displayName?.let {
        Text(
          text = it,
          color = Color.Gray,
          style = MaterialTheme.typography.body2,
          overflow = TextOverflow.Ellipsis
        )
      }
    }
  }
}