package com.playloudr.app.view.screens.profile.myprofile

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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

@Composable
fun MyProfileScreen(
  viewModel: MyProfileViewModel,
  navController: NavController
) {
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

      when (val profileState = viewModel.profileState.collectAsState().value) {
        is ProfileState.RefreshLoading -> LoadingIndicator()
        is ProfileState.ProfileLoaded -> {
          MyProfileTopBar(user = profileState.user) {
            showDrawer = !showDrawer
          }
          Spacer(modifier = Modifier.height(16.dp))
          ProfilePosts(
            profileState.user,
            profileState.posts,
            profileState.profileInfo,
            imagePickerLauncher,
            permissionLauncher,
            navController,
            showDrawer,
            null,
            { viewModel.logout() },
            SessionManager.getCurrentUser() != null && SessionManager.getCurrentUser() != profileState.user.username,
            null
          )
        }
        is ProfileState.Error -> Text(text = profileState.exception.message!!)
      }
    }
  }
}
