package com.playloudr.app.view.components

import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.playloudr.app.view.theme.PLLogoColor

@Composable
fun FollowButton(
  isFollowed: Boolean,
  onFollowClicked: () -> Unit
) {
  if (isFollowed) {
    Button(
      onClick = onFollowClicked,
      colors = ButtonDefaults.buttonColors(backgroundColor = PLLogoColor)
    ) {
      Text("Following", color = PLLogoColor)
    }
  } else {
    OutlinedButton(
      onClick = onFollowClicked,
      colors = ButtonDefaults.outlinedButtonColors(contentColor = PLLogoColor)
    ) {
      Text("Follow")
    }
  }
}

