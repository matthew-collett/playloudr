package com.playloudr.app.view.screens.feed

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
  val debouncer = remember { Debouncer(waitMs = 50, coroutineScope) }
  val showTopBar by feedViewModel.showTopBar.collectAsState(true)
  // user search
  val showSearchBar by feedViewModel.showSearchBar.collectAsState(false)
  val searchResults by feedViewModel.userSearchResults.collectAsState()


  Column {
    // Will need to pass view model into FeedTopBar to make user search work
    AnimatedVisibility(
      visible = showTopBar,
      enter = slideInVertically(initialOffsetY = { -it }),
      exit = slideOutVertically(targetOffsetY = { -it })
    ) {
      FeedTopBar(
        showSearchBar = showSearchBar,
        onSearchIconClicked = { feedViewModel.onSearchIconClicked() },
        feedViewModel = feedViewModel
      )
    }
    if (showSearchBar ) {
      // Search results are available, display them
      LazyColumn {
        if (feedViewModel.hasSearched.value && searchResults.isEmpty()) {
          item {
            Text(
              text = "No user found",
              fontWeight = FontWeight.Bold,
              color = Color.Gray, // Customize the text color
              fontSize = 18.sp,   // Customize the font size
              textAlign = TextAlign.Center,
              modifier = Modifier
                .fillMaxWidth()  // Expand the Text composable to fill the available width
                .padding(16.dp)
            )
          }
        }
        else {
          items(searchResults) { user ->
            UserRow(user, navController) // Can make composable for each user to display result
            //Text(text = user.username)
          }
        }
      }
    }
    else{
      ScrollAwareLazyColumn(
        modifier = Modifier
          .fillMaxWidth(),
        scrollUp = {
          debouncer.debounce { feedViewModel.onScrollUp() }
        },
        scrollDown = {
          debouncer.debounce { feedViewModel.onScrollDown() }
        }
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

