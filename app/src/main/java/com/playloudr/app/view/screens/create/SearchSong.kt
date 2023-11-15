package com.playloudr.app.view.screens.create

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import coil.compose.rememberImagePainter

@Composable
fun SearchSong(viewModel: ViewModel) {

//  var songName by remember { mutableStateOf("") }
//
//  Column {
//    TextField(
//      value = songName,
//      onValueChange = { songName = it },
//      label = { Text("Enter song name") }
//    )
//    Button(onClick = { viewModel.searchSong(songName) }) {
//      Text("Search")
//    }
//    val imageUrl = viewModel.songImageUrl.collectAsState()
//    imageUrl.value?.let { url:String ->
//      Image(painter = rememberImagePainter(data = url), contentDescription = "Album Cover")
//    }
//  }
}