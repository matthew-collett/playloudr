package com.playloudr.app.view.screens.profile.personal

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.playloudr.app.model.entities.reecher

@OptIn(ExperimentalCoilApi::class, ExperimentalPermissionsApi::class)
@Composable
fun ProfileHeader(
  imageUrl: String,
  name: String,
  bio: String?,
  onImageClick: () -> Unit
) {
  Column(
    modifier = Modifier
      .padding(horizontal = 16.dp)
      .fillMaxWidth(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    Image(
      painter = rememberImagePainter(data = imageUrl),
      contentDescription = "Profile Picture",
      contentScale = ContentScale.Crop,
      modifier = Modifier
        .size(100.dp)
        .clip(CircleShape)
        .clickable {
          onImageClick()
        }
    )
    Text(
      text = name,
      style = MaterialTheme.typography.h1,
      fontWeight = FontWeight.SemiBold,
      fontSize = 20.sp,
      color = Color.Black
    )
    Row (
      modifier = Modifier
        .fillMaxWidth(),
      horizontalArrangement = Arrangement.Center,
      verticalAlignment = Alignment.CenterVertically,
    ) {
      ProfileInfo(num = 300, label = "Followers")
      Spacer(modifier = Modifier.width(16.dp))
      ProfileInfo(num = 70, label = "Following")
      Spacer(modifier = Modifier.width(16.dp))
      ProfileInfo(num = 421, label = "Posts")
    }
    if (bio != null) {
      Text(
        text = bio,
        textAlign = TextAlign.Center
      )
    }
  }
}


@Composable
fun ProfileInfo(num: Int, label: String) {
  Column(horizontalAlignment = Alignment.CenterHorizontally) {
    Text(
      text = num.toString(),
      fontWeight = FontWeight.SemiBold,
      style = MaterialTheme.typography.h6 
    )
    Text(
      text = label,
      style = MaterialTheme.typography.caption 
    )
  }
}

@Composable
@Preview
fun ProfileHeaderPreview() {
//  ProfileHeader(
//    imageUrl = reecher.profilePictureUrl,
//    name = reecher.username,
//    bio = reecher.bio
//  )
}