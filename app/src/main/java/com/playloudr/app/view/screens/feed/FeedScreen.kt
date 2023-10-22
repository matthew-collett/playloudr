package com.playloudr.app.view.screens.feed

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.playloudr.app.R
import com.playloudr.app.model.entities.PostEntity

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FeedScreen(postList: List<PostEntity>, modifier: Modifier) {
  Scaffold(
    topBar = {
      TopAppBar(
        title = {
          Image(
            painter = painterResource(id = R.drawable.ic_playloudr_logo),
            contentDescription = "App Logo",
            modifier = Modifier
              .size(120.dp),
            contentScale = ContentScale.Crop
          )
                },
        backgroundColor = Color.White,
        actions = {
          IconButton(onClick = { /*TODO*/ }) {
            Icon(
              painter = painterResource(id = R.drawable.ic_playloudr_search_icon),
              contentDescription = "search bar",
              modifier = Modifier
                .size(18.dp)
            )
          }
        }
      )
    }
  ) {
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
          post.type,
          post.profilePictureUrl
        )
      }
    }
  }
}


