package com.playloudr.app.view.screens.profile.myprofile

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.playloudr.app.service.SessionManager
import com.playloudr.app.view.components.LoadingIndicator
import com.playloudr.app.view.navigation.BottomNavigationBar
import com.playloudr.app.view.screens.profile.ProfilePosts
import com.playloudr.app.view.screens.profile.ProfileState
import com.playloudr.app.viewmodel.MyProfileViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterialApi::class)
@Composable
fun MyProfileScreen(
  viewModel: MyProfileViewModel,
  navController: NavController
) {
  val profileState by viewModel.profileState.collectAsState()
  val pullRefreshState = rememberPullRefreshState(profileState is ProfileState.RefreshLoading, { viewModel.refreshProfile() })
  var showDrawer by remember { mutableStateOf(false) }
  val scope = rememberCoroutineScope()
  val imagePickerLauncher = rememberLauncherForActivityResult(
    contract = ActivityResultContracts.GetContent()
  ) { uri ->
    uri?.let { nonNullUri ->
      scope.launch {
        viewModel.updateProfilePicture(nonNullUri.toString())
      }
    }
  }

  val permissionLauncher = rememberLauncherForActivityResult(
    contract = ActivityResultContracts.RequestPermission()
  ) { isGranted ->
    if (isGranted) {
      imagePickerLauncher.launch("image/*")
    } else {
      return@rememberLauncherForActivityResult
    }
  }
  Scaffold(
    bottomBar = { BottomNavigationBar(navController = navController) }
  ) { paddingValues ->
    Column(
      modifier = Modifier.padding(paddingValues)
    ) {
      when (val currState = profileState) {
        is ProfileState.RefreshLoading -> LoadingIndicator()
        is ProfileState.ProfileLoaded -> {
          MyProfileTopBar(user = currState.user) {
            showDrawer = !showDrawer
          }
          Box(Modifier.pullRefresh(pullRefreshState)) {
            ProfilePosts(
              currState.user,
              currState.posts,
              currState.profileInfo,
              imagePickerLauncher,
              permissionLauncher,
              navController,
              showDrawer,
              null,
              { viewModel.logout() },
              SessionManager.getCurrentUser() != null && SessionManager.getCurrentUser() != currState.user.username,
              null
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
