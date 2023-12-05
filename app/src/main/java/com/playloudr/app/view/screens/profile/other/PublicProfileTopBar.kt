package com.playloudr.app.view.screens.profile.other

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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun PublicProfileTopBar(
  username:String,
  navController: NavController
) {
  TopAppBar(
    backgroundColor = Color.White,
    modifier = Modifier
      .height(48.dp),
    navigationIcon = {
      IconButton(onClick = { navController.navigateUp() }) {
        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
      }
    },
    title = {
      Row(
        modifier = Modifier
          //.fillMaxSize(),
        //.background(Color.White),
          .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        //horizontalArrangement = Arrangement.Center,
        horizontalArrangement = Arrangement.Center

        ) {
        Text(
          text = username,
          style = MaterialTheme.typography.h1,
          fontWeight = FontWeight.Bold,
          fontSize = 20.sp,
          color = Color.Black
        )
        Spacer(Modifier.weight(1f))
      }
    }
  )
}


//@Composable
//@Preview
//fun ProfileTopBarPreview() {
//  ProfileTopBar(reecher.username)
//}