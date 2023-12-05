package com.playloudr.app.view.screens.create

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.playloudr.app.view.components.LoadingIndicator
import com.playloudr.app.view.navigation.BottomNavigationBar
import com.playloudr.app.view.screens.Screen
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
        title = { Text("Create a new post") },
        backgroundColor = Color.White,
        contentColor = Color.Black
      )
    },
    bottomBar = { BottomNavigationBar(navController = navController) }
  ) { paddingValues ->
    Column(
      modifier = Modifier.padding(paddingValues)
    ) {
      TextField(
        value = searchQuery,
        onValueChange = { newValue: String -> viewModel.searchQuery.value = newValue },
        label = { Text("Select a Song") }
      )
      OutlinedTextField(
        value = caption,
        onValueChange = { caption = it },
        label = { Text("Bio") }
      )
      if (errorMessage.isNotEmpty()) {
        Text(errorMessage, color = Color.Red)
      }
      when (createPostState) {
        is CreatePostScreenState.Idle -> {}
        is CreatePostScreenState.Loading -> LoadingIndicator()
        is CreatePostScreenState.Success -> {
          if (!isSongSelected) {
            LazyColumn(
              modifier = Modifier.fillMaxWidth()
            ) {
              items(createPostState.songs) { song ->
                CreatePostSongItem(song = song, onSelect = { viewModel.onSongSelected(it) })
              }
            }
          }
        }
        is CreatePostScreenState.Error -> Text(text = createPostState.exception.message!!)
      }
      Button(
        onClick = {
          errorMessage = if (createPostState is CreatePostScreenState.Success && createPostState.selectedSong != null) {
            viewModel.createPost(createPostState.selectedSong, caption)
            navController.navigate(Screen.Feed.route)
            ""
          } else {
            "Please select a song"
          }
        },
      ) {
        Text(text = "Create Post")
      }
    }
  }
}