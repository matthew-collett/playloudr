package com.playloudr.app.view.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.playloudr.app.viewmodel.SearchViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchUserBar(viewModel: SearchViewModel) {
  val searchQuery by viewModel.searchQuery.collectAsState()

  Box(
    modifier = Modifier
      .fillMaxWidth()
      .background(Color.White)
      .padding(8.dp)
      .border(
        width = 1.dp,
        color = Color.Gray,
        shape = RoundedCornerShape(8.dp)
      )
  ) {
    TextField(
      value = searchQuery,
      onValueChange = { newValue: String -> viewModel.searchQuery.value = newValue },
      placeholder = { Text("Search for users...") },
      modifier = Modifier
        .fillMaxWidth(),
      singleLine = true,
      trailingIcon = {
        Icon(
          imageVector = Icons.Default.Search,
          contentDescription = "Search for a friend!",
          tint = Color.Gray
        )
      },
      colors = TextFieldDefaults.textFieldColors(
        backgroundColor = Color.Transparent,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent
      )
    )
  }
}
