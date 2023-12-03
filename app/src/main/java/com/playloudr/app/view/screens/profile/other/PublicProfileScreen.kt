package com.playloudr.app.view.screens.profile.other

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.playloudr.app.model.entities.reecher
import com.playloudr.app.model.entities.reecherPosts
import com.playloudr.app.viewmodel.ProfileViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PublicProfileScreen(
  viewModel: ProfileViewModel,
  navController: NavController
  ) {
  var showDrawer by remember { mutableStateOf(false) }
  val scaffoldState = rememberScaffoldState()
  val scope = rememberCoroutineScope()

  // this info will be taken from view model
  // for top bar
  val displayName = reecher.username
  // for header
  val profilePic = reecher.profilePictureUrl
  val name = reecher.username
  val bio = reecher.bio
  // lazy col
  val scrollState = rememberLazyListState()

  val onLogoutClick = {
    // Handle logout click holder
  }

  Scaffold(
    scaffoldState = scaffoldState,
    topBar = {
      PublicProfileTopBar(
        navController = navController,
        username = displayName!!
      )
    },

  ) {
    Box(Modifier.padding(vertical = 16.dp)) {
      LazyColumn(
        state = scrollState,
        modifier = Modifier
          .fillMaxSize()
          .background(Color.White),
        verticalArrangement = Arrangement.spacedBy(16.dp)
      ) {
        // profile picture, name, followers, following, posts, bio
        item {
          PublicProfileHeader(profilePic, name, bio)
        }

        // Grid of posts
        gridItems(
          data = reecherPosts,
          columnCount = 2
        ) { post ->
          PublicProfilePostCard(post) { clickedPost ->
            navController.navigate("postDetail/${clickedPost.postId}")
          }
        }

      }
      AnimatedVisibility(
        visible = showDrawer,
        enter = slideInHorizontally(initialOffsetX = { fullWidth -> fullWidth }),
        exit = slideOutHorizontally(targetOffsetX = { fullWidth -> fullWidth }),
        modifier = Modifier
          .align(Alignment.CenterEnd)
          .background(Color.White)
      ) {
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
