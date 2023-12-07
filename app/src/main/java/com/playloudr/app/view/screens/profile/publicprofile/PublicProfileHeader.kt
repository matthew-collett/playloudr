package com.playloudr.app.view.screens.profile.publicprofile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.playloudr.app.view.components.FollowButton
import com.playloudr.app.viewmodel.PublicProfileViewModel

@OptIn(ExperimentalCoilApi::class)
@Composable
fun PublicProfileHeader(
  user: UserEntity,
  profileInfo: ProfileInfoEntity,
  toggleFollowStatus: () -> Unit,
  isFollowed: Boolean
) {
  Column(
    modifier = Modifier
      .padding(horizontal = 16.dp)
      .fillMaxWidth(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.spacedBy(16.dp)
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
    FollowButton(
      isFollowed = isFollowed,
      onFollowClicked = {
        toggleFollowStatus()
      }
    )
    Row (
      modifier = Modifier
        .fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceEvenly,
      verticalAlignment = Alignment.CenterVertically,
    ) {
      ProfileInfo(num = profileInfo.numFollowers, label = "Followers")
      Spacer(modifier = Modifier.width(16.dp))
      ProfileInfo(num = profileInfo.numFollowing, label = "Following")
      Spacer(modifier = Modifier.width(16.dp))
      ProfileInfo(num = profileInfo.numPosts, label = "Posts")
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