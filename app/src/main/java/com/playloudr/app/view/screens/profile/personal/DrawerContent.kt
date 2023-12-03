package com.playloudr.app.view.screens.profile.personal

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun DrawerContent(onLogoutClick: () -> Unit) {
  Column(modifier = Modifier.fillMaxSize()) {
    TextButton(onClick = onLogoutClick) {
      Text(
        text = "Logout",
        color = Color.Black
      )
    }
  }
}