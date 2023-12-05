package com.playloudr.app.view.screens.feed

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.playloudr.app.model.entities.PostEntity
import com.playloudr.app.view.components.LoadingIndicator
import com.playloudr.app.viewmodel.FeedViewModel

@Composable
fun FeedScreen(
  postList: List<PostEntity>,
  feedViewModel: FeedViewModel,
  navController: NavController
) {

  // user search
  val showSearchBar by feedViewModel.showSearchBar.collectAsState(false)

  val feedState by feedViewModel.feedState.collectAsState()
  val searchState by feedViewModel.searchState.collectAsState()


  Column {
    FeedTopBar(
      showSearchBar = showSearchBar,
      onSearchIconClicked = { feedViewModel.onSearchIconClicked() },
      feedViewModel = feedViewModel
    )

    if (showSearchBar) {
      when (val currentState = searchState) {
        is SearchState.Loading -> {
          LoadingIndicator()
        }

        is SearchState.Loaded -> {
          LazyColumn {
            items(currentState.users) { user ->
              UserRow(user, navController)
            }
          }
        }
        is SearchState.NoResults -> {
          Text(
            text = "No user found",
            fontWeight = FontWeight.Bold,
            color = Color.Gray, // Customize the text color
            fontSize = 18.sp,   // Customize the font size
            textAlign = TextAlign.Center,
            modifier = Modifier
              .fillMaxWidth()  // Expand the Text composable to fill the available width
              .padding(16.dp)
          )
        }
        is SearchState.Error -> {
          val exception = (searchState as SearchState.Error).exception
          Text("Error: ${exception.message}")
        }
        else -> Unit
      }
    }
    else {
      when (feedState) {
        is FeedState.RefreshLoading -> {
          LoadingIndicator()
        }
        is FeedState.NoPosts -> {
          Text(
            text = (feedState as FeedState.NoPosts).reason,
            modifier = Modifier.align(Alignment.CenterHorizontally)
          )
        }
        is FeedState.Error -> {
          val exception = (feedState as FeedState.Error).exception
          Text(
            text = "Error: ${exception.message}",
            modifier = Modifier.align(Alignment.CenterHorizontally)
          )
        }
        is FeedState.PostsLoaded -> {
          val posts = (feedState as FeedState.PostsLoaded).posts
          LazyColumn(
            modifier = Modifier
              .fillMaxWidth(),
          ) {
            // TODO: change postList to posts when dynamo ready
            items(postList) { post ->
              PostCard(post, navController)
            }
          }
        }
      }
    }
  }
}


