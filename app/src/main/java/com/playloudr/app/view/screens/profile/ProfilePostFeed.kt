package com.playloudr.app.view.screens.profile

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.playloudr.app.model.entities.PostEntity

@Composable
fun ProfilePostFeed(posts: List<PostEntity>) {

  LazyVerticalGrid(
    columns = GridCells.Fixed(2), // Set the number of columns
    contentPadding = PaddingValues(4.dp), // Add padding around the grid
  ) {
    items(posts) { post ->
      ProfilePostCard(post = post)
    }
  }
}
