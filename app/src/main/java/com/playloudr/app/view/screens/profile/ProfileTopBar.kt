package com.playloudr.app.view.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.playloudr.app.model.entities.reecher

@Composable
fun ProfileTopBar(
  username:String,
  onMenuClick: () -> Unit
) {
  var showMenu by remember { mutableStateOf(false) }
  TopAppBar(
    backgroundColor = Color.White,
    modifier = Modifier
      .height(48.dp),
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
    },
    actions = {
      IconButton(onClick = onMenuClick) {
        Icon(Icons.Default.MoreVert, contentDescription = "more menu")
      }
    }
  )
}


//@Composable
//@Preview
//fun ProfileTopBarPreview() {
//  ProfileTopBar(reecher.username)
//}