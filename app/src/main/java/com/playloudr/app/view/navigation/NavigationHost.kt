package com.playloudr.app.view.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

import androidx.navigation.compose.composable
import com.playloudr.app.model.entities.posts
import com.playloudr.app.model.repository.PostRepository
import com.playloudr.app.view.screens.Screen
import com.playloudr.app.view.screens.create.CreatePostScreen
import com.playloudr.app.view.screens.feed.FeedScreen1
import com.playloudr.app.view.screens.profile.ProfileScreen
import com.playloudr.app.viewmodel.CreatePostViewModel
import com.playloudr.app.viewmodel.FeedViewModel
import com.playloudr.app.viewmodel.ProfileViewModel

@Composable
fun NavigationHost(navController: NavHostController, modifier: Modifier) {
  NavHost(
    navController = navController,
    startDestination = Screen.Feed.route,
    modifier = modifier
  ) {
    composable(Screen.Feed.route) {
      val viewModel: FeedViewModel = FeedViewModel(PostRepository)
      FeedScreen1(posts)
    }
    composable(Screen.CreatePost.route) {
      val viewModel: CreatePostViewModel = CreatePostViewModel() // Adjust this according to your DI setup
      CreatePostScreen(viewModel)
    }
    composable(Screen.Profile.route) {
      val viewModel: ProfileViewModel = ProfileViewModel() // Adjust this according to your DI setup
      ProfileScreen(viewModel)
    }
  }
}
