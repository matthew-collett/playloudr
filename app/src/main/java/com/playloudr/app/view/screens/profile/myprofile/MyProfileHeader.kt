package com.playloudr.app.view.screens.profile.myprofile

import android.net.Uri
import android.os.Build
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.playloudr.app.model.entity.ProfileInfoEntity
import com.playloudr.app.model.entity.UserEntity

@OptIn(ExperimentalCoilApi::class)
@Composable
fun MyProfileHeader(
  user: UserEntity,
  profileInfo: ProfileInfoEntity,
  imagePickerLauncher: ManagedActivityResultLauncher<String, Uri?>,
  permissionLauncher: ManagedActivityResultLauncher<String, Boolean>
) {
  Column(
    modifier = Modifier
      .fillMaxWidth(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    Column(
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Box(
        modifier = Modifier
          .size(100.dp)
          .background(color = Color.Gray, shape = CircleShape)
      ) {
        Spacer(modifier = Modifier.height(16.dp))
        Image(
          painter = rememberImagePainter(data = user.profilePictureUrl),
          contentDescription = "Profile Picture",
          contentScale = ContentScale.Crop,
          modifier = Modifier
            .matchParentSize()
            .padding(1.dp)
            .clip(CircleShape)
            .clickable {
              if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) {
                permissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
              } else {
                imagePickerLauncher.launch("image/*")
              }
            }
        )
      }
      Spacer(modifier = Modifier.height(4.dp))
      Text(
        text = "Edit",
        fontSize = 14.sp,
        color = Color.Gray
      )
    }


    if (user.displayName != null) {
      Text(
        text = user.displayName,
        style = MaterialTheme.typography.h1,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        color = Color.Black
      )
    }
    if (user.bio != null) {
      Text(
        text = user.bio,
        textAlign = TextAlign.Center
      )
    }

    Row(
      modifier = Modifier
        .fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceEvenly,
      verticalAlignment = Alignment.CenterVertically,
    ) {
      ProfileInfo(num = profileInfo.numFollowers, label = "Followers", Modifier.weight(1f))
      ProfileInfo(num = profileInfo.numFollowing, label = "Following", Modifier.weight(1f))
      ProfileInfo(num = profileInfo.numPosts, label = "Posts", Modifier.weight(1f))
    }
  }
}


@Composable
fun ProfileInfo(num: Int, label: String, modifier: Modifier = Modifier) {
  Column(
    modifier = modifier,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
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