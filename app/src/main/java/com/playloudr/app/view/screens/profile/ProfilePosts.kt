package com.playloudr.app.view.screens.profile

import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.playloudr.app.model.entity.PostEntity
import com.playloudr.app.model.entity.ProfileInfoEntity
import com.playloudr.app.model.entity.UserEntity
import com.playloudr.app.view.screens.Screen
import com.playloudr.app.view.screens.profile.myprofile.MyProfileDrawerContent
import com.playloudr.app.view.screens.profile.myprofile.MyProfileHeader
import com.playloudr.app.view.screens.profile.publicprofile.PublicProfileHeader

@Composable
fun ProfilePosts(
  user: UserEntity,
  posts: List<PostEntity>,
  profileInfo: ProfileInfoEntity,
  imagePickerLauncher: ManagedActivityResultLauncher<String, Uri?>?,
  permissionLauncher: ManagedActivityResultLauncher<String, Boolean>?,
  navController: NavController,
  showDrawer: Boolean?,
  toggleFollowStatus: (() -> Unit)?,
  logout: (() -> Unit)?,
  isPublic: Boolean,
  isFollowed: Boolean?
) {
  Box(Modifier.padding(vertical = 16.dp)) {
    LazyColumn(
      modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
      verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
      item {
        if (isPublic) {
          PublicProfileHeader(user, profileInfo, toggleFollowStatus!!, isFollowed!!)
        } else {
          MyProfileHeader(user, profileInfo, imagePickerLauncher!!, permissionLauncher!!)
        }

      }
      if (posts.isNotEmpty()) {
        gridItems(
          data = posts,
          columnCount = 2
        ) { post ->
          ProfilePostItem(post) { clickedPost ->
            navController.navigate(
              Screen.PostDetail.createRoute(
                clickedPost.username,
                clickedPost.timestamp
              )
            )
          }
        }
      } else {
        item {
          Column(
            horizontalAlignment = Alignment.CenterHorizontally
          ) {
            Icon(
              imageVector = Icons.Outlined.Info,
              modifier = Modifier.size(64.dp),
              tint = Color.Gray,
              contentDescription = "Notification"
            )
            Text(
              text = "No posts yet",
              fontWeight = FontWeight.Bold,
              color = Color.Gray,
              fontSize = 18.sp,
              textAlign = TextAlign.Center,
              modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
            )
          }
        }
      }
    }
    if (!isPublic) {
      AnimatedVisibility(
        visible = showDrawer!!,
        enter = slideInHorizontally(initialOffsetX = { fullWidth -> fullWidth }),
        exit = slideOutHorizontally(targetOffsetX = { fullWidth -> fullWidth }),
        modifier = Modifier
          .align(Alignment.CenterEnd)
          .background(Color.White)
      ) {
        MyProfileDrawerContent(
          onLogoutClick = {
            if (logout != null) {
              logout()
            }
            navController.navigate(Screen.SignIn.route)
          }
        )
      }
    }
  }
}

fun <T> LazyListScope.gridItems(
  data: List<T>,
  columnCount: Int,
  itemContent: @Composable (T) -> Unit
) {
  val rows = data.windowed(columnCount, columnCount, true)
  rows.forEach { row ->
    item {
      Row {
        for (item in row) {
          Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
            itemContent(item)
          }
        }
        if (row.size < columnCount) {
          for (i in 0 until (columnCount - row.size)) {
            Box(
              modifier = Modifier
                .weight(1f)
                .aspectRatio(1f)
            )
          }
        }
      }
    }
  }
}

