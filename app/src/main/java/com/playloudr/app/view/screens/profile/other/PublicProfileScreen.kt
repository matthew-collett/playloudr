package com.playloudr.app.view.screens.profile.other

import android.Manifest
import android.annotation.SuppressLint
import android.os.Build
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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.playloudr.app.model.entities.reecher
import com.playloudr.app.model.entities.reecherPosts
import com.playloudr.app.view.components.LoadingIndicator
import com.playloudr.app.view.screens.profile.ProfileState
import com.playloudr.app.view.screens.profile.personal.ProfileHeader
import com.playloudr.app.view.screens.profile.personal.ProfileTopBar
import com.playloudr.app.viewmodel.ProfileViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PublicProfileScreen(
  viewModel: ProfileViewModel,
  navController: NavController
  ) {
  val profileState by viewModel.profileState.collectAsState()

  val scaffoldState = rememberScaffoldState()

  // this info will be taken from view model
  // keeping hard coded for now
  // for top bar
  val username = reecher.username // viewmodel.getUsername()
  // for header
  val profilePic = reecher.profilePictureUrl
  val name = reecher.displayName
  val bio = reecher.bio
  // lazy col
  val scrollState = rememberLazyListState()


  when (val currentState = profileState) {
    is ProfileState.PostsLoaded -> {
      Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
          PublicProfileTopBar(
            navController = navController,
            username = username
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
              // example of getting posts from view model
              data = currentState.posts,
              columnCount = 2
            ) { post ->
              PublicProfilePostCard(post) { clickedPost ->
                navController.navigate("postDetail/${clickedPost.postId}")
              }
            }

          }
        }
      }
    }
    is ProfileState.NoPosts -> {
      Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
          PublicProfileTopBar(username = username, navController)
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
              PublicProfileHeader(
                imageUrl = profilePic,
                name = name,
                bio = bio
              )
            }
            item {
              Text(
                text = "This user hasn't posted yet",
                fontWeight = FontWeight.Bold,
                color = Color.Gray, // Customize the text color
                fontSize = 18.sp,   // Customize the font size
                textAlign = TextAlign.Center,
                modifier = Modifier
                  .fillMaxWidth()  // Expand the Text composable to fill the available width
                  .padding(16.dp)
              )
            }
          }
        }
      }
    }
    is ProfileState.RefreshLoading -> {
      LoadingIndicator()
    }
    is ProfileState.Error -> {
      val exception = (profileState as ProfileState.Error).exception
      Text(
        text = "Error: ${exception.message}",
      )
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
