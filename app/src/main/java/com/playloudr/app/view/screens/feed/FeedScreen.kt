package com.playloudr.app.view.screens.feed

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.playloudr.app.service.SessionManager
import com.playloudr.app.view.components.LoadingIndicator
import com.playloudr.app.view.components.PostCard
import com.playloudr.app.view.navigation.BottomNavigationBar
import com.playloudr.app.viewmodel.FeedViewModel

@Composable
fun FeedScreen(
  viewModel: FeedViewModel,
  navController: NavController
) {
  Scaffold(
    bottomBar = { BottomNavigationBar(navController = navController) }
  ) { paddingValues ->
    Column(
      modifier = Modifier.padding(paddingValues)
    ) {
      FeedTopBar(showSearchBar = false, onSearchIconClicked = {})
      when (val feedState = viewModel.feedState.collectAsState().value) {
        is FeedState.RefreshLoading -> LoadingIndicator()
        is FeedState.PostsLoaded -> {
          LazyColumn(
            modifier = Modifier.fillMaxWidth()
          ) {
            items(feedState.posts) { post ->
              PostCard(post = post, navController, SessionManager.getCurrentUser() != post.username)
            }
          }
        }
        is FeedState.NoPosts -> Text(text = feedState.reason)
        is FeedState.Error -> Text(text = feedState.exception.message!!)
      }
    }
  }
}


