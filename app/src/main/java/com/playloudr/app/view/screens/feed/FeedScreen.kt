package com.playloudr.app.view.screens.feed

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
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
import com.playloudr.app.service.SessionManager
import com.playloudr.app.view.components.LoadingIndicator
import com.playloudr.app.view.components.PostCard
import com.playloudr.app.view.navigation.BottomNavigationBar
import com.playloudr.app.viewmodel.FeedViewModel

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterialApi::class)
@Composable
fun FeedScreen(
  viewModel: FeedViewModel,
  navController: NavController
) {
  val feedState by viewModel.feedState.collectAsState()
  val pullRefreshState = rememberPullRefreshState(feedState is FeedState.RefreshLoading, { viewModel.refreshFeedPosts() })

  Scaffold(
    bottomBar = { BottomNavigationBar(navController = navController) }
  ) { paddingValues ->
    Column(
      modifier = Modifier.padding(paddingValues)
    ) {
      FeedTopBar(navController)
      Box(Modifier.pullRefresh(pullRefreshState)) {
        when (val currState = feedState) {
          is FeedState.RefreshLoading -> LoadingIndicator()
          is FeedState.PostsLoaded -> {
            LazyColumn(
              modifier = Modifier.fillMaxWidth()
            ) {
              items(currState.posts) { post ->
                PostCard(
                  post = post,
                  navController,
                  SessionManager.getCurrentUser() != post.username
                )
              }
            }
          }
          is FeedState.NoPosts -> {
            LazyColumn(
              modifier = Modifier
                .fillMaxSize()
                .pullRefresh(pullRefreshState)
            ) {
              item {
                Box(
                  modifier = Modifier
                    .fillParentMaxSize(),
                  contentAlignment = Alignment.Center
                ) {
                  Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                  ) {
                    Icon(
                      imageVector = Icons.Outlined.Notifications,
                      modifier = Modifier.size(64.dp),
                      tint = Color.Gray,
                      contentDescription = "Notification"
                    )
                    Text(
                      text = currState.reason,
                      fontWeight = FontWeight.Bold,
                      color = Color.Gray,
                      fontSize = 18.sp,
                      textAlign = TextAlign.Center,
                      modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                    )
                  }
                }
              }
            }
          }
          is FeedState.Error -> Text(text = currState.exception.message!!)
        }
        PullRefreshIndicator(
          refreshing = feedState is FeedState.RefreshLoading,
          state = pullRefreshState,
          modifier = Modifier.align(Alignment.TopCenter)
        )
      }
    }
  }
}

