package com.playloudr.app.view.screens.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.playloudr.app.model.entities.PostEntity
import com.playloudr.app.model.entities.posts
import com.playloudr.app.model.entities.reecher
import com.playloudr.app.model.entities.reecherPosts
import com.playloudr.app.util.DateTimeUtils.formatTimestamp
import com.playloudr.app.view.screens.signin.mockNavController
import com.playloudr.app.view.theme.PlayloudrTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalCoilApi::class)
@Composable
fun ProfilePostDetail(
  post: PostEntity,
  //viewModel: ViewModel,
  postId: String,
  navController: NavController
) {
  Scaffold (
    topBar = {
      TopAppBar(
        backgroundColor = Color.White,
        modifier = Modifier
          .height(48.dp),
        title = {
          Text(
            text = post.username,
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
      Column(
        modifier = Modifier
          .padding(vertical = 8.dp)
          .fillMaxWidth()
      ) {
        Row(
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.Bottom,
          modifier = Modifier
            .padding(end = 8.dp)
            .fillMaxWidth()
        ) {
          Row {
            IconButton(onClick = { /* Handle click */ }) {
              Icon(Icons.Default.PlayArrow, contentDescription = "Play Music")
            }
            Column {
              Text(
                text = post.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
              )
              Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                  .fillMaxWidth()
              ) {
                Text(
                  text = post.artist,
                  fontWeight = FontWeight.Bold,
                  color = Color.Gray
                )
                Text(
                  text = post.postType.name,
                  fontStyle = FontStyle.Italic,
                  fontWeight = FontWeight.Bold,
                  color = Color(0xFF414FB3)
                )
              }
            }
          }
        }

        Image(
          painter = rememberImagePainter(data = post.imageUrl),
          contentDescription = "Post Image",
          contentScale = ContentScale.Crop,
          modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .size(200.dp)
        )

        Column(
          modifier = Modifier
            .padding(8.dp)
        ) {
          Row {
            Text(
              text = post.username,
              fontWeight = FontWeight.Bold
            )
            Spacer(
              modifier = Modifier
                .width(4.dp)
            )
            post.caption?.let { Text(text = it) }
          }
          Text(
            text = formatTimestamp(post.timestamp),
            fontSize = 12.sp,
            color = Color.Gray
          )
        }
      }
    }

  )
}

@Preview
@Composable
fun ProfilePostDetailPreview() {
  ProfilePostDetail(reecherPosts[0], "1", mockNavController())
}