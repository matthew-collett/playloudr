package com.playloudr.app.view.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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
      Text("Following", color = Color.White)
    }
  } else {
    OutlinedButton(
      onClick = onFollowClicked,
      colors = ButtonDefaults.outlinedButtonColors(contentColor = PLLogoColor),
      border = BorderStroke(1.dp, PLLogoColor) // Set the border color to match the text
    ) {
      Text("Follow", color = PLLogoColor)
    }

  }
}

