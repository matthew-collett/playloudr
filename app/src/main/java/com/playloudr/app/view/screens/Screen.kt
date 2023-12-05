package com.playloudr.app.view.screens

import android.net.Uri
import com.playloudr.app.R
import java.time.Instant

sealed class Screen(val route: String, val filledIcon: Int?, val outlineIcon: Int?) {
  object Feed : Screen("feed", R.drawable.ic_playloudr_home, R.drawable.ic_playloudr_house_door_outline)
  object CreatePost : Screen("createPost", R.drawable.ic_playloudr_plus, R.drawable.ic_playloudr_plus_outline)
  object MyProfile : Screen("myProfile", R.drawable.ic_playloudr_user, R.drawable.ic_playloudr_user_outline)
  object Search : Screen("search", R.drawable.ic_playloudr_search_icon, R.drawable.ic_playloudr_search_icon)
  object SignIn : Screen("signIn", null, null)
  object SignUp : Screen("signUp", null, null)
  object PostDetail : Screen("postDetail/{username}/{timestamp}", null, null) {
    fun createRoute(username: String, timestamp: Instant) =
      "postDetail/${Uri.encode(username)}/${Uri.encode(timestamp.toString())}"
  }
  object PublicProfile : Screen("publicProfile/{username}", null, null) {
    fun createRoute(username: String) = "publicProfile/$username"
  }
}

