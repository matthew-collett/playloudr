package com.playloudr.app.view.screens.profile.publicprofile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.playloudr.app.service.SessionManager
import com.playloudr.app.view.components.LoadingIndicator
import com.playloudr.app.view.navigation.BottomNavigationBar
import com.playloudr.app.view.screens.feed.FeedState
import com.playloudr.app.view.screens.profile.ProfilePosts
import com.playloudr.app.view.screens.profile.ProfileState
import com.playloudr.app.viewmodel.PublicProfileViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PublicProfileScreen(
  viewModel: PublicProfileViewModel,
  navController: NavController
) {
  val profileState by viewModel.profileState.collectAsState()
  val pullRefreshState = rememberPullRefreshState(
    profileState is ProfileState.RefreshLoading,
    { viewModel.refreshProfile() })

  Scaffold(
    bottomBar = { BottomNavigationBar(navController = navController) }
  ) { paddingValues ->
    Column(
      modifier = Modifier.padding(paddingValues)
    ) {
      val isFollowed by viewModel.isFollowed.collectAsState()
      when (val currState = profileState) {
        is ProfileState.RefreshLoading -> LoadingIndicator()
        is ProfileState.ProfileLoaded -> {
          PublicProfileTopBar(currState.user, navController)
          Box(Modifier.pullRefresh(pullRefreshState)) {
            ProfilePosts(
              currState.user,
              currState.posts,
              currState.profileInfo,
              null,
              null,
              navController,
              null,
              { viewModel.toggleFollowStatus() },
              null,
              SessionManager.getCurrentUser() != currState.user.username,
              isFollowed
            )
            PullRefreshIndicator(
              refreshing = profileState is ProfileState.RefreshLoading,
              state = pullRefreshState,
              modifier = Modifier
                .align(Alignment.TopCenter)
            )
          }
        }

        is ProfileState.Error -> Text(text = currState.exception.message!!)
      }
    }
  }
}