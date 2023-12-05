package com.playloudr.app.view.screens.create

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.playloudr.app.viewmodel.CreatePostViewModel
import com.playloudr.app.viewmodel.SpotifyViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CreatePostScreenAgain(
  viewModel: CreatePostViewModel,
  spotVM: SpotifyViewModel,
  navController: NavController
) {
  var caption by remember { mutableStateOf(TextFieldValue()) }
  var showSearchSong by remember { mutableStateOf(false) }

  Scaffold(
    topBar = {
      TopAppBar(
        title = { Text("Create a new post") },
        backgroundColor = Color.White,
        contentColor = Color.Black
      )
    },
    floatingActionButton = {
      FloatingActionButton(
        onClick = { /* Handle post action here */ }
      ) {
        Text("Post")
      }
    }
  ) {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    ) {
      // User profile and text input area
      Row(
        verticalAlignment = Alignment.CenterVertically
      ) {
        // Maybe show profile pic
//        Icon(
//          imageVector = Icons.Default.AccountCircle,
//          contentDescription = "Profile picture",
//          modifier = Modifier.size(48.dp)
//        )
        Spacer(modifier = Modifier.width(8.dp))
        TextField(
          value = caption,
          onValueChange = { caption = it },
          placeholder = { Text("Express your thoughts...") },
          modifier = Modifier.fillMaxWidth()
        )
      }

      Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
      ) {
        TextButton(
          onClick = { showSearchSong = !showSearchSong },
          modifier = Modifier
        ) {
          Text("Search Album Cover")
        }

        Spacer(modifier = Modifier.width(16.dp))

        if (showSearchSong) {
          // TODO figure out viewmodel
          SearchSong(viewModel = spotVM)
        }

      }
    }
  }
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//  CreatePostScreen()
//}