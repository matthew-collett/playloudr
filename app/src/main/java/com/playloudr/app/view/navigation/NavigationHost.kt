package com.playloudr.app.view.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.playloudr.app.service.SessionManager
import com.playloudr.app.view.screens.Screen
import com.playloudr.app.view.screens.create.CreatePostScreen
import com.playloudr.app.view.screens.feed.FeedScreen
import com.playloudr.app.view.screens.postdetail.PostDetailScreen
import com.playloudr.app.view.screens.profile.myprofile.MyProfileScreen
import com.playloudr.app.view.screens.profile.publicprofile.PublicProfileScreen
import com.playloudr.app.view.screens.search.SearchScreen
import com.playloudr.app.view.screens.signin.SignInScreen
import com.playloudr.app.view.screens.signup.SignUpScreen
import com.playloudr.app.viewmodel.CreatePostViewModel
import com.playloudr.app.viewmodel.FeedViewModel
import com.playloudr.app.viewmodel.MyProfileViewModel
import com.playloudr.app.viewmodel.PostDetailViewModel
import com.playloudr.app.viewmodel.PublicProfileViewModel
import com.playloudr.app.viewmodel.SearchViewModel
import com.playloudr.app.viewmodel.SignInViewModel
import com.playloudr.app.viewmodel.SignUpViewModel


@Composable
fun NavigationHost(
  navController: NavHostController
) {
  NavHost(
    navController = navController,
    startDestination = if (SessionManager.isLoggedIn()) Screen.Feed.route else Screen.SignIn.route
  ) {
    composable(Screen.Feed.route) {
      val viewModel: FeedViewModel = viewModel()
      FeedScreen(viewModel = viewModel, navController = navController)
    }
    composable(Screen.MyProfile.route) {
      val viewModel: MyProfileViewModel = viewModel()
      MyProfileScreen(viewModel, navController)
    }
    composable(
      route = Screen.PublicProfile.route,
      arguments = listOf( navArgument("username") { type = NavType.StringType })
      ) { backStackEntry ->
      val username = backStackEntry.arguments?.getString("username") ?: return@composable
      val viewModel: PublicProfileViewModel = viewModel()
      LaunchedEffect(username) {
        viewModel.init(username)
      }
      PublicProfileScreen(viewModel, navController)
    }
    composable(
      route = Screen.PostDetail.route,
      arguments = listOf(
        navArgument("username") { type = NavType.StringType },
        navArgument("timestamp") { type = NavType.StringType }
      )
    ) { backStackEntry ->
      val username = backStackEntry.arguments?.getString("username") ?: return@composable
      val timestamp = backStackEntry.arguments?.getString("timestamp") ?: return@composable
      val viewModel: PostDetailViewModel = viewModel()
      LaunchedEffect(id) {
        viewModel.init("$username/$timestamp")
      }
      PostDetailScreen(viewModel, navController)
    }
    composable(Screen.SignIn.route) {
      val viewModel: SignInViewModel = viewModel()
      SignInScreen(viewModel = viewModel, navController = navController)
    }
    composable(Screen.SignUp.route) {
      val viewModel:SignUpViewModel = viewModel()
      SignUpScreen(viewModel,navController)
    }
    composable(Screen.CreatePost.route) {
      val viewModel: CreatePostViewModel = viewModel()
      CreatePostScreen(viewModel = viewModel, navController = navController)
    }
    composable(Screen.Search.route) {
      val viewModel: SearchViewModel = viewModel()
      SearchScreen(viewModel, navController)
    }
  }
}
