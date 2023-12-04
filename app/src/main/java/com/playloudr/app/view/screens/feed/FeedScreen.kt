package com.playloudr.app.view.screens.feed

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.playloudr.app.model.entities.PostEntity
import com.playloudr.app.util.Debouncer
import com.playloudr.app.view.components.ScrollAwareLazyColumn
import com.playloudr.app.viewmodel.FeedViewModel

@Composable
fun FeedScreen(
  postList: List<PostEntity>,
  feedViewModel: FeedViewModel,
  navController: NavController
) {
  val coroutineScope = rememberCoroutineScope()
  // 15 ms optimal
  val debouncer = remember { Debouncer(waitMs = 12, coroutineScope) }
  // user search
  val showSearchBar by feedViewModel.showSearchBar.collectAsState(false)

  //val showTopBar by feedViewModel.showTopBar.collectAsState(true)

  val searchResults by feedViewModel.userSearchResults.collectAsState()

  val listState = rememberLazyListState()
  // Define thresholds and heights
  val someThreshold = 160 // threshold for scroll offset
  val collapsedHeight = 0.dp // Height of AppBar when collapsed
  val expandedHeight = 50.dp // Normal height of the AppBar
  val minOpacity = 0.7f // Min opacity of the AppBar
  val maxOpacity = 1f // Max opacity of the AppBar

  // Calculate the appBarHeight and appBarAlpha based on scroll position
  val appBarHeight = remember { derivedStateOf {
    if (listState.firstVisibleItemScrollOffset > someThreshold) collapsedHeight else expandedHeight
  }}
  val appBarAlpha = remember { derivedStateOf {
    if (listState.firstVisibleItemScrollOffset > someThreshold) minOpacity else maxOpacity
  }}



  Column {
    // Will need to pass view model into FeedTopBar to make user search work
//    AnimatedVisibility(
//      visible = showTopBar,
//      enter = slideInVertically(initialOffsetY = { -it }),
//      exit = slideOutVertically(targetOffsetY = { -it })
//    ) {
      FeedTopBar(
        showSearchBar = showSearchBar,
        onSearchIconClicked = { feedViewModel.onSearchIconClicked() },
        feedViewModel = feedViewModel,
        appBarHeight = appBarHeight.value,
        appBarAlpha = appBarAlpha.value
      )
    //}
    if (showSearchBar && searchResults.isNotEmpty()) {
      // Search results are available, display them
      LazyColumn() {
        items(searchResults) { user ->
          UserRow(user, navController) // Can make composable for each user to display result
          //Text(text = user.username)
        }
      }
    }
    else{
      //ScrollAwareLazyColumn(
      LazyColumn(
        state = listState,
        modifier = Modifier
          .fillMaxWidth(),
//        scrollUp = {
//          debouncer.debounce { feedViewModel.onScrollUp() }
//        },
//        scrollDown = {
//          debouncer.debounce { feedViewModel.onScrollDown() }
//        }
      ) {
        items(postList) { post ->
          PostCard(post, navController)
        }
      }
    }
  }

//  ScrollAwareLazyColumn(
//    modifier = Modifier.fillMaxWidth(),
//    scrollUp = { debouncer.debounce(onScrollUp) },
//    scrollDown = { debouncer.debounce(onScrollDown) }
//  ) {
//    items(postList) { post ->
//      PostCard(post)
//    }
//  }
}

