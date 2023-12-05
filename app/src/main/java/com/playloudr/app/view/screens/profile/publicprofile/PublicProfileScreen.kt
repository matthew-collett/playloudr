package com.playloudr.app.view.screens.profile.publicprofile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.playloudr.app.service.SessionManager
import com.playloudr.app.view.components.LoadingIndicator
import com.playloudr.app.view.navigation.BottomNavigationBar
import com.playloudr.app.view.screens.profile.ProfilePosts
import com.playloudr.app.view.screens.profile.ProfileState
import com.playloudr.app.viewmodel.PublicProfileViewModel

@Composable
fun PublicProfileScreen(
  viewModel: PublicProfileViewModel,
  navController: NavController
) {
  Scaffold(
    bottomBar = { BottomNavigationBar(navController = navController) }
  ) { paddingValues ->
    Column(
      modifier = Modifier.padding(paddingValues)
    ) {
      val isFollowed by viewModel.isFollowed.collectAsState()
      when (val profileState = viewModel.profileState.collectAsState().value) {
        is ProfileState.RefreshLoading -> LoadingIndicator()
        is ProfileState.ProfileLoaded -> {
          PublicProfileTopBar(profileState.user, navController)
          Spacer(modifier = Modifier.height(16.dp))
          ProfilePosts(
            profileState.user,
            profileState.posts,
            profileState.profileInfo,
            null,
            null,
            navController,
            null,
            { viewModel.toggleFollowStatus() },
            null,
            SessionManager.getCurrentUser() != profileState.user.username,
            isFollowed
          )
        }
        is ProfileState.Error -> Text(text = profileState.exception.message!!)
      }
    }
  }
}
