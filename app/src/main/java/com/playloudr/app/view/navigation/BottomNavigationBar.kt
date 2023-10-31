package com.playloudr.app.view.navigation

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.playloudr.app.view.screens.Screen

@Composable
fun BottomNavigationBar(navController: NavController) {
  val screens: List<Screen> = listOf(Screen.Feed, Screen.CreatePost, Screen.Profile)
  val navBackStackEntry by navController.currentBackStackEntryAsState()
  val currentRoute = navBackStackEntry?.destination?.route
  BottomNavigation(
    backgroundColor = Color.White,
    modifier = Modifier
      .height(48.dp)
  ) {
    screens.forEach { screen ->
      BottomNavigationItem(
        icon = {
          val icon = if (currentRoute == screen.route) {
            screen.filledIcon
          } else {
            screen.outlineIcon
          }
          icon?.let { painterResource(it) }?.let {
            Icon(
              painter = it,
              tint = Color.Black,
              contentDescription = screen.route,
              modifier = Modifier
                .size(20.dp)
            )
          }
        },
        selected = currentRoute == screen.route,
        onClick = {
          if (currentRoute != screen.route) {
            navController.navigate(screen.route) {
              popUpTo(navController.graph.startDestinationId) {
                saveState = true
              }
              launchSingleTop = true
              restoreState = true
            }
          }
        }
      )
    }
  }
}
