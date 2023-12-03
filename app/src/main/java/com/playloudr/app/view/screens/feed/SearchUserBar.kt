package com.playloudr.app.view.screens.feed

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.playloudr.app.viewmodel.FeedViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(feedViewModel: FeedViewModel) {
  var searchText by remember { mutableStateOf("") }
  val keyboardController = LocalSoftwareKeyboardController.current

  TextField(
    value = searchText,
    onValueChange = { searchText = it },
    placeholder = { Text("Search for users...") },
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 8.dp),
    textStyle = TextStyle(fontSize = 15.sp),
    singleLine = true,
    trailingIcon = {
      IconButton(onClick = { feedViewModel.onUserSearchQuery(searchText) }) {
        Icon(imageVector = Icons.Default.Search, contentDescription = "Search for a friend!")
      }
    },
    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
    keyboardActions = KeyboardActions(
      onSearch = {
      feedViewModel.onUserSearchQuery(searchText)
      },
      onDone = {keyboardController?.hide()}
  ),
  )
}