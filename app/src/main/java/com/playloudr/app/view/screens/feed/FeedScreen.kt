package com.playloudr.app.view.screens.feed

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.playloudr.app.model.entities.PostEntity
import com.playloudr.app.util.Debouncer
import com.playloudr.app.view.components.ScrollAwareLazyColumn

@Composable
fun FeedScreen(
  postList: List<PostEntity>,
  onScrollDown: () -> Unit,
  onScrollUp: () -> Unit
) {
  val coroutineScope = rememberCoroutineScope()
  // 15 ms optimal
  val debouncer = remember { Debouncer(waitMs = 15, coroutineScope) }
  // user search
  var showSearchBar by remember { mutableStateOf(false) }

  Column {
    if (showSearchBar) {
      SearchBar(onSearch = {/*TODO HANDLE QUERY HERE, THROUGH VIEW MODEL?*/ })
    }
    FeedTopBar(onSearchIconClicked = {showSearchBar = !showSearchBar})

    ScrollAwareLazyColumn(
      modifier = Modifier.fillMaxWidth(),
      scrollUp = { debouncer.debounce(onScrollUp) },
      scrollDown = { debouncer.debounce(onScrollDown) }
    ) {
      items(postList) { post ->
        PostCard(post)
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

