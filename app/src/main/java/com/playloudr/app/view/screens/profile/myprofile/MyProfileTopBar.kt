package com.playloudr.app.view.screens.profile.myprofile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.playloudr.app.model.entity.UserEntity

@Composable
fun MyProfileTopBar(
  user: UserEntity,
  onMenuClick: () -> Unit
) {
  TopAppBar(
    backgroundColor = Color.White,
    modifier = Modifier
      .height(48.dp),
    title = {
      Row(
        modifier = Modifier
          .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center

        ) {
        Text(
          text = user.username,
          style = MaterialTheme.typography.h1,
          fontWeight = FontWeight.Bold,
          fontSize = 20.sp,
          color = Color.Black
        )
        Spacer(Modifier.weight(1f))
      }
    },
    actions = {
      IconButton(onClick = onMenuClick) {
        Icon(Icons.Default.MoreVert, contentDescription = "More Menu")
      }
    }
  )
}