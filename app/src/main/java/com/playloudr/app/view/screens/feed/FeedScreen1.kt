package com.playloudr.app.view.screens.feed

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.playloudr.app.model.entities.PostEntity

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FeedScreen1(postList: List<PostEntity>) {
  LazyColumn(
    modifier = Modifier
      .fillMaxWidth(),
    //.padding(bottom = 60.dp),
    //contentPadding = PaddingValues(8.dp), // padding for width of post
    verticalArrangement = Arrangement.spacedBy(16.dp) // padding for spacing between posts
  ) {
//      val dummyList = List(10) {"Item $it"}
//      items(dummyList) { item ->
//        Text(text = item, modifier = Modifier.padding(8.dp))
//      }

    items(postList) { post ->
      PostCard(
        post.username,
        post.timestamp,
        post.title,
        post.artist,
        post.caption,
        post.imageUrl,
        post.audioUrl,
        post.postType,
        post.profilePictureUrl
      )
    }
  }
}

