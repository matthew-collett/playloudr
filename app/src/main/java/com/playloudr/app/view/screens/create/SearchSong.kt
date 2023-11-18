package com.playloudr.app.view.screens.create

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.playloudr.app.R
import com.playloudr.app.viewmodel.SpotifyViewModel


@OptIn(ExperimentalComposeUiApi::class, ExperimentalCoilApi::class)
@Composable
fun SearchSong(viewModel: SpotifyViewModel) {
  // State for the text field
  var searchText by remember { mutableStateOf("") }
  // State for the image URL
  val songImageUrl by viewModel.songImageUrl.collectAsState()
  val keyboardController = LocalSoftwareKeyboardController.current

  Column {
    TextField(
      value = searchText,
      onValueChange = { searchText = it },
      label = { Text("Enter song name") },
      modifier = Modifier.fillMaxWidth(),
      singleLine = true,
      keyboardOptions = KeyboardOptions.Default.copy(
        imeAction = androidx.compose.ui.text.input.ImeAction.Search
      ),
      keyboardActions = KeyboardActions(onSearch = {
        if (searchText.isNotBlank()) {
          viewModel.searchSong(searchText)
          // Dismiss keyboard
          keyboardController?.hide()
        }
      })
    )
    Button(
      onClick = {
        if (searchText.isNotBlank()) {
          viewModel.searchSong(searchText)
        }
      },
      modifier = Modifier.align(Alignment.End)
    ) {
      Text("Search")
    }
    songImageUrl?.let { imageUrl ->
      Log.d("SearchComposable", "imageUrl is $imageUrl")
      Image(
        painter = rememberImagePainter(
          data = imageUrl,
          builder = {
            placeholder(R.drawable.ic_playloudr_logo)
            error("failed to load $imageUrl")
          }
        ),
        contentDescription = "Song Image",
        modifier = Modifier
          .fillMaxWidth()
          .height(200.dp)
      )
    }
  }
}
