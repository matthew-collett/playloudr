package com.playloudr.app.view.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.playloudr.app.model.entities.posts
import com.playloudr.app.model.repository.PostRepository
import com.playloudr.app.view.screens.Screen
import com.playloudr.app.view.screens.feed.FeedScreen
import com.playloudr.app.view.screens.profile.personal.ProfileScreen
import com.playloudr.app.view.screens.signin.SignInScreen
import com.playloudr.app.view.screens.signin.SignUpScreen
import com.playloudr.app.viewmodel.CreatePostViewModel
import com.playloudr.app.viewmodel.FeedViewModel
import com.playloudr.app.viewmodel.ProfileViewModel
import com.playloudr.app.view.screens.create.CreatePostScreenAgain
import com.playloudr.app.view.screens.profile.other.PublicProfileScreen
import com.playloudr.app.view.screens.profile.personal.ProfilePostDetail
import com.playloudr.app.viewmodel.SpotifyViewModel


@Composable
fun NavigationHost(
  navController: NavHostController,
  modifier: Modifier
) {
  NavHost(
    navController = navController,
    startDestination = Screen.Feed.route,
    modifier = modifier
  ) {
    composable(Screen.Feed.route) {
      val viewModel: FeedViewModel = viewModel()
      FeedScreen(
        postList = posts,
        feedViewModel = viewModel,
        navController = navController
      )
    }
    composable(Screen.CreatePost.route) {
      val viewModel: CreatePostViewModel = viewModel()
      val spotVM: SpotifyViewModel = viewModel()
      CreatePostScreenAgain(viewModel, spotVM, navController)
    }
    composable(Screen.Profile.route) {
      val viewModel: ProfileViewModel = viewModel()
      // TODO get logged in user through UserRepository or through a UserViewModel
      // val username = UserRepository.getLoggedInUsername()
      // viewModel.setUsername(username)
      // LaunchedEffect(username) {
        // viewModel.setUsername(username)
      // }

      viewModel.setUsername("Reecher")
      ProfileScreen(viewModel, navController)
    }
    composable(Screen.SignIn.route) {
      SignInScreen(navController)
    }
    composable(Screen.SignUp.route) {
      SignUpScreen(navController)
    }
    composable(
      route = Screen.PostDetail.route,
      arguments = listOf(navArgument("postId") { type = NavType.StringType })
    ) { backStackEntry ->
      // if null return to prev composable
      val postId = backStackEntry.arguments?.getString("postId") ?: return@composable
      val post = PostRepository.getPostByIdTemp(postId) ?: return@composable

      ProfilePostDetail(post = post, postId = postId, navController = navController)
    }
    composable(Screen.PublicProfile.route) { backStackEntry ->
      // Retrieve the user ID from the arguments
      val username = backStackEntry.arguments?.getString("username") ?: return@composable
      // TODO We can have a setUsername function instead of creating a view model factory
      val viewModel: ProfileViewModel = viewModel()
      // doing this so we don't need to incorporate a view model factory
      // this makes sure we profile view model gets username
      LaunchedEffect(username) {
        viewModel.setUsername(username)
      }
      PublicProfileScreen(viewModel, navController)
    }
  }
}
