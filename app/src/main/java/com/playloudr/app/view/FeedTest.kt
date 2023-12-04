package com.playloudr.app.view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.playloudr.app.model.entity.PostEntity
import com.playloudr.app.view.screens.feed.FeedState
import com.playloudr.app.viewmodel.FeedViewModel

@Composable
fun FeedView(viewModel: FeedViewModel) {
  // Observing the feedState from the ViewModel
  val feedState by viewModel.feedState.collectAsState()

  when (feedState) {
    is FeedState.RefreshLoading -> LoadingView()
    is FeedState.NoPosts -> Text((feedState as FeedState.NoPosts).reason)
    is FeedState.PostsLoaded -> PostListView(posts = (feedState as FeedState.PostsLoaded).posts)
    is FeedState.Error -> Text("Error: ${(feedState as FeedState.Error).exception.message}")
  }
}

@Composable
fun PostListView(posts: List<PostEntity>) {
  LazyColumn {
    items(posts) { post ->
      PostItemView(post)
    }
  }
}

@Composable
fun PostItemView(post: PostEntity) {
  // Define how each post should be displayed
  Text(post.title) // Assuming Post has a content field
}

@Composable
fun LoadingView() {
  // Define your loading view
  CircularProgressIndicator()
}
