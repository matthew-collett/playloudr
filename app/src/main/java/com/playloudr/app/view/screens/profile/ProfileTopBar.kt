package com.playloudr.app.view.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.playloudr.app.model.entities.reecher

@Composable
fun ProfileTopBar(username:String ) {
  TopAppBar(
    backgroundColor = Color.White,
    modifier = Modifier
      .height(48.dp),
    title = {
      Row(
        modifier = Modifier
          .fillMaxSize(),
        //.background(Color.White),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,

        ) {
        Text(
          text = username,
          style = MaterialTheme.typography.h1,
          fontWeight = FontWeight.Bold,
          fontSize = 20.sp,
          color = Color.Black
        )
      }
    }
  )
}


@Composable
@Preview
fun ProfileTopBarPreview() {
  ProfileTopBar(reecher.username)
}