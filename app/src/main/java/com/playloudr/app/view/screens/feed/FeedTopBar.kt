package com.playloudr.app.view.screens.feed

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.playloudr.app.R
import com.playloudr.app.view.theme.Modulus

@Composable
fun FeedTopBar() {
  TopAppBar(
    backgroundColor = Color.White,
    title = {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
      ) {
        Image(
          painter = painterResource(id = R.drawable.ic_playloudr_icon),
          contentDescription = "PlayLoudr Logo",
          contentScale = ContentScale.Crop,
          modifier = Modifier.size(28.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
          text = "PlayLoudr",
          fontFamily = Modulus,
          fontSize = 18.sp,
          fontWeight = FontWeight.Bold
        )
      }

    },
    actions = {
      IconButton(onClick = { /*TODO*/ }) {
        Icon(
          painter = painterResource(id = R.drawable.ic_playloudr_search_icon),
          contentDescription = "Search",
          modifier = Modifier.size(24.dp),
          tint = Color.Black
        )
      }
    }
  )
}

@Preview
@Composable
fun FeedTopBarPreview() {
  FeedTopBar()
}