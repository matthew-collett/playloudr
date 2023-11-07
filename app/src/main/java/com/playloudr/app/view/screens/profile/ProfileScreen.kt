package com.playloudr.app.view.screens.profile

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.playloudr.app.model.entities.reecher
import com.playloudr.app.model.entities.reecherPosts
import com.playloudr.app.view.screens.feed.FeedTopBar
import com.playloudr.app.viewmodel.ProfileViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProfileScreen(
  viewModel: ProfileViewModel,
  navController: NavController
  ) {
  // for top bar
  val displayName = reecher.displayName
  // for header
  val profilePic = reecher.profilePictureUrl
  val name = reecher.username
  val bio = reecher.bio
  // lazy col
  val scrollState = rememberLazyListState()
  LazyColumn(
    state = scrollState,
    modifier = Modifier
      .fillMaxSize()
      .background(Color.White),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    // username (header)
    stickyHeader { ProfileTopBar(displayName!!) }

    // profile picture, name, followers, following, posts, bio
    item {
      ProfileHeader(profilePic, name, bio)
    }

    // Grid of posts
    gridItems(
      data = reecherPosts,
      columnCount = 2
    ) { post ->
      ProfilePostCard(post) { clickedPost ->
        navController.navigate("postDetail/${clickedPost.postId}")
      }
    }


  }

}

// helper function to implement profile grid
fun <T> LazyListScope.gridItems(
  data: List<T>,
  columnCount: Int,
  itemContent: @Composable (T) -> Unit
) {
  val rows = data.windowed(columnCount, columnCount, true)
  rows.forEach { row ->
    item {
      Row {
        for (item in row) {
          Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
            itemContent(item)
          }
        }
        if (row.size < columnCount) {
          for (i in 0 until (columnCount - row.size)) {
            Box(modifier = Modifier
              .weight(1f)
              .aspectRatio(1f)) // Keep the aspect ratio consistent
          }
        }
      }
    }
  }
}
