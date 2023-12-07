package com.playloudr.app.view.screens.create

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.playloudr.app.view.components.LoadingIndicator
import com.playloudr.app.view.navigation.BottomNavigationBar
import com.playloudr.app.view.screens.Screen
import com.playloudr.app.view.theme.PLLogoColor
import com.playloudr.app.viewmodel.CreatePostViewModel

@Composable
fun CreatePostScreen(viewModel: CreatePostViewModel, navController: NavController) {
  val createPostState = viewModel.createPostState.collectAsState().value
  var caption by remember { mutableStateOf("") }
  var errorMessage by remember { mutableStateOf("") }
  val searchQuery by viewModel.searchQuery.collectAsState()
  val isSongSelected by viewModel.isSongSelected.collectAsState()
  Scaffold(
    topBar = {
      TopAppBar(
        title = {
          Row(
            verticalAlignment = Alignment.CenterVertically
          ) {
            Icon(
              imageVector = Icons.Outlined.Add,
              modifier = Modifier.size(28.dp),
              contentDescription = "Add"
            )
            Text(
              text = "Create Post",
              style = MaterialTheme.typography.h1,
              fontWeight = FontWeight.Bold,
              fontSize = 20.sp,
              color = Color.Black
            )
          }
        },
        backgroundColor = Color.White,
        modifier = Modifier
          .height(48.dp),
      )
    },
    bottomBar = { BottomNavigationBar(navController = navController) }
  ) { paddingValues ->
    Column(
      modifier = Modifier
        .padding(paddingValues)
        .fillMaxSize(),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Spacer(modifier = Modifier.height(16.dp))
      OutlinedTextField(
        value = searchQuery,
        onValueChange = {
          newValue: String -> viewModel.searchQuery.value = newValue
          viewModel.isSongSelected.value = false
        },
        label = { Text("Select Song") }
      )
      when (createPostState) {
        is CreatePostScreenState.Idle -> {}
        is CreatePostScreenState.Loading -> LoadingIndicator()
        is CreatePostScreenState.Success -> {
          if (!isSongSelected) {
            LazyColumn(
              modifier = Modifier
                .fillMaxSize()
            ) {
              items(createPostState.songs) { song ->
                CreatePostSongItem(song = song, onSelect = { viewModel.onSongSelected(it) })
              }
            }
          }
        }
        is CreatePostScreenState.Error -> Text(text = createPostState.exception.message!!)
      }
      Spacer(modifier = Modifier.height(16.dp))
      OutlinedTextField(
        value = caption,
        onValueChange = { caption = it },
        label = { Text("Caption") },
      )
      Spacer(modifier = Modifier.height(32.dp))
      if (errorMessage.isNotEmpty()) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(errorMessage, color = Color.Red)
      }
      Button(
        enabled = createPostState is CreatePostScreenState.Success && createPostState.selectedSong != null,
        onClick = {
          errorMessage = if (createPostState is CreatePostScreenState.Success && createPostState.selectedSong != null) {
            viewModel.createPost(createPostState.selectedSong, caption)
            navController.navigate(Screen.Feed.route)
            ""
          } else {
            "Error while creating post, please try again later"
          }
        },
        colors = ButtonDefaults.buttonColors(
          backgroundColor = PLLogoColor,
          contentColor = Color.White
        ),
        modifier = Modifier
          .height(48.dp)
          .width(280.dp)
      ) {
        Text(text = "Create Post")
      }
    }
  }
}