package com.playloudr.app.view.screens

import com.playloudr.app.R

sealed class Screen(val route: String, val filledIcon: Int?, val outlineIcon: Int?) {
  object Feed : Screen("feed", R.drawable.ic_playloudr_home, R.drawable.ic_playloudr_house_door_outline)
  object CreatePost : Screen("createPost", R.drawable.ic_playloudr_plus, R.drawable.ic_playloudr_plus_outline)
  object Profile : Screen("profile", R.drawable.ic_playloudr_user, R.drawable.ic_playloudr_user_outline)
  object Search : Screen("search", R.drawable.ic_playloudr_search_icon, R.drawable.ic_playloudr_search_icon)
  object SignIn : Screen("signIn", null, null)
  object SignUp : Screen("signUp", null, null)
}

