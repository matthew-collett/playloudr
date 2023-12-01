package com.playloudr.app.view.screens.feed

import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction

@Composable
fun SearchBar(onSearch: (String) -> Unit) {
  var searchText by remember { mutableStateOf("") }

  TextField(
    value = searchText,
    onValueChange = { searchText = it },
    placeholder = { Text("Search for users...") },
    modifier = Modifier.fillMaxWidth(),
    singleLine = true,
    trailingIcon = {
      IconButton(onClick = { onSearch(searchText) }) {
        Icon(imageVector = Icons.Default.Search, contentDescription = "Search for a friend!")
      }
    },
    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
    keyboardActions = KeyboardActions(onSearch = {
      onSearch(searchText)
      // You can also handle the keyboard dismissal here if needed
    })
  )
}