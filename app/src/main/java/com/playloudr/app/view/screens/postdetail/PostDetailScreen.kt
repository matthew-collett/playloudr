package com.playloudr.app.view.screens.postdetail

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.playloudr.app.model.entity.PostEntity
import com.playloudr.app.service.SessionManager
import com.playloudr.app.view.components.LoadingIndicator
import com.playloudr.app.view.components.PostCard
import com.playloudr.app.viewmodel.PostDetailViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PostDetailScreen(
  viewModel: PostDetailViewModel,
  navController: NavController
) {
  when (val postDetailState = viewModel.postDetailState.collectAsState().value) {
    is PostDetailState.RefreshLoading -> LoadingIndicator()
    is PostDetailState.PostLoaded -> {
      Scaffold (
        topBar = {
          TopAppBar(
            backgroundColor = Color.White,
            modifier = Modifier
              .height(48.dp),
            title = {
              Text(
                text = postDetailState.post.username,
                style = MaterialTheme.typography.h1,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.Black,
              )
            },
            navigationIcon = {
              IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
              }
            }
          )
        },
        content = {
          PostCard(
            postDetailState.post,
            navController,
            SessionManager.getCurrentUser() != postDetailState.post.username
          )
        }
      )
    }
    is PostDetailState.Error -> Text(text = postDetailState.exception.message!!)
  }
}
