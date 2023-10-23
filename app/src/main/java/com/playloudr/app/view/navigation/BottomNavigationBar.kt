package com.playloudr.app.view.navigation

import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.playloudr.app.view.screens.Screen

@Composable
fun BottomNavigationBar(navController: NavController, currentRoute: String?) {
  val screens: List<Screen> = listOf(Screen.Feed, Screen.CreatePost, Screen.Profile)

  BottomNavigation(
    backgroundColor = Color.White
  ) {
    screens.forEach { screen ->
      BottomNavigationItem(
        icon = {
          val icon = if (currentRoute == screen.route) {
            screen.filledIcon
          } else {
            screen.outlineIcon
          }
          Icon(
            painter = painterResource(icon),
            modifier = Modifier.size(24.dp),
            contentDescription = null)
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
