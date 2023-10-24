package com.playloudr.app.view.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun ScrollAwareLazyColumn(
  modifier: Modifier = Modifier,
  scrollUp: () -> Unit,
  scrollDown: () -> Unit,
  content: LazyListScope.() -> Unit
) {
  val scrollState = rememberLazyListState()

  val currentOffset = remember { derivedStateOf { scrollState.firstVisibleItemScrollOffset } }
  val previousOffset = remember { mutableIntStateOf(0) }

  LaunchedEffect(currentOffset.value) {
    if (currentOffset.value > previousOffset.intValue) {
      scrollDown()
    } else if (currentOffset.value < previousOffset.intValue) {
      scrollUp()
    }
    previousOffset.intValue = currentOffset.value
  }

  LazyColumn(modifier = modifier, state = scrollState, content = content)
}


