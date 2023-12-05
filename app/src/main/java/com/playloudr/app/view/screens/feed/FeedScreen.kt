package com.playloudr.app.view.screens.feed

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
      FeedTopBar(navController)
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
        is FeedState.NoPosts -> {
          Box(
            modifier = Modifier.fillMaxSize(),
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
                text = feedState.reason,
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
        is FeedState.Error -> Text(text = feedState.exception.message!!)
      }
    }
  }
}


