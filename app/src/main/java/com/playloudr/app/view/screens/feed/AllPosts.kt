package com.playloudr.app.view.screens.feed

import FeedHeader
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.playloudr.app.model.entities.PostEntity
import com.playloudr.app.view.theme.PlayloudrTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AllPosts(postList: List<PostEntity>) {
  Scaffold(
    topBar = {
      TopAppBar(
        { FeedHeader() }
        //navigationIcon = null
      )
    }
  ) {
    LazyColumn(
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = (8.dp)),
      contentPadding = PaddingValues(16.dp)
    ) {
      items(postList) { post ->
        PlayloudrTheme {
          PostCard(
            post.username,
            post.timestamp,
            post.title,
            post.artist,
            post.caption,
            post.imageUrl,
            post.audioUrl,
            post.type
          )
        }
      }
    }
  }
}


