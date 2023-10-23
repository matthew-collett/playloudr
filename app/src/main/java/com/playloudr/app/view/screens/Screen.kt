package com.playloudr.app.view.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.playloudr.app.R

sealed class Screen(val route: String, val filledIcon: Int, val outlineIcon: Int) {
  object Feed : Screen("feed", R.drawable.ic_playloudr_home, R.drawable.ic_playloudr_house_door_outline)
  object CreatePost : Screen("createPost", R.drawable.ic_playloudr_plus, R.drawable.ic_playloudr_plus_outline)
  object Profile : Screen("profile", R.drawable.ic_playloudr_user, R.drawable.ic_playloudr_user_outline)
  object Search : Screen("search", R.drawable.ic_playloudr_search_icon, R.drawable.ic_playloudr_search_icon)
}

