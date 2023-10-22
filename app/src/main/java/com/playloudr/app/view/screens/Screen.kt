package com.playloudr.app.view.screens

sealed class Screen(val route: String) {
  object Feed : Screen("feed")
  object Profile : Screen("profile")
  object Launch : Screen("launch")
}
