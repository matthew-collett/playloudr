package com.playloudr.app.view.screens.feed

import FeedHeader
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.playloudr.app.model.entities.PostEntity
import com.playloudr.app.view.theme.PlayloudrTheme
import com.playloudr.app.view.theme.myColors

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FeedScreen(postList: List<PostEntity>, modifier: Modifier) {
  Scaffold(
    topBar = {
      TopAppBar(
        title = { FeedHeader() },
        backgroundColor = Color.White,
        //navigationIcon = null
      )
    }
    //bottomBar = { BottomBar() }
  ) {
    LazyColumn(
      modifier = Modifier
        .fillMaxWidth(),
        //.padding(bottom = 60.dp),
      //contentPadding = PaddingValues(8.dp), // padding for width of post
      verticalArrangement = Arrangement.spacedBy(16.dp) // padding for spacing between posts
    ) {
      items(postList) { post ->
        PostCard(
          post.username,
          post.timestamp,
          post.title,
          post.artist,
          post.caption,
          post.imageUrl,
          post.audioUrl,
          post.type,
          post.profilePictureUrl
        )
      }
    }
  }
}


