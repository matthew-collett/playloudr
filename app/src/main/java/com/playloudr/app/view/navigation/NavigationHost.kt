package com.playloudr.app.view.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.playloudr.app.model.entity.posts
import com.playloudr.app.model.repository.PostRepository
import com.playloudr.app.view.screens.Screen
import com.playloudr.app.view.screens.feed.FeedScreen
import com.playloudr.app.view.screens.profile.ProfileScreen
import com.playloudr.app.view.screens.signin.SignInScreen
import com.playloudr.app.view.screens.signin.SignUpScreen
import com.playloudr.app.viewmodel.CreatePostViewModel
import com.playloudr.app.viewmodel.FeedViewModel
import com.playloudr.app.viewmodel.ProfileViewModel
import com.playloudr.app.view.screens.profile.ProfilePostDetail
import com.playloudr.app.viewmodel.SpotifyViewModel


@Composable
fun NavigationHost(
  navController: NavHostController,
  modifier: Modifier,
  onScrollDown: () -> Unit,
  onScrollUp: () -> Unit
) {
  NavHost(
    navController = navController,
    startDestination = Screen.Feed.route,
    modifier = modifier
  ) {
    composable(Screen.Feed.route) {
      val viewModel: FeedViewModel = FeedViewModel(PostRepository)
      FeedScreen(
        postList = posts,
        onScrollDown = onScrollDown,
        onScrollUp = onScrollUp
      )
    }
    composable(Screen.Profile.route) {
      val viewModel: ProfileViewModel = ProfileViewModel()
      ProfileScreen(viewModel, navController)
    }
    composable(Screen.SignIn.route) {
      SignInScreen(navController)
    }
    composable(Screen.SignUp.route) {
      SignUpScreen(navController)
    }

  }
}
