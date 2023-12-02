package com.playloudr.app.view.screens.feed

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.playloudr.app.R
import com.playloudr.app.view.theme.Modulus

@Composable
fun FeedTopBar(showSearchBar: Boolean, onSearchIconClicked: () -> Unit) {
  TopAppBar(
    backgroundColor = Color.White,
    modifier = Modifier
      .height(48.dp),
    title = {
      if (showSearchBar) {
        SearchBar(onSearch = {/*TODO HANDLE QUERY HERE, THROUGH VIEW MODEL, passed in through param*/ })
      }
      else {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.Start,
          verticalAlignment = Alignment.CenterVertically,
        ) {
          Image(
            painter = painterResource(id = R.drawable.ic_playloudr_icon),
            contentDescription = "PlayLoudr Logo",
            contentScale = ContentScale.Crop,
            modifier = Modifier
              .size(32.dp)
          )
          Spacer(
            modifier = Modifier
              .width(4.dp)
          )
          Text(
            text = "Playloudr",
            fontFamily = Modulus,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
          )
        }
    }
    },
    actions = {
      IconButton(onClick = onSearchIconClicked) {
        Icon(
          painter = painterResource(id = R.drawable.ic_playloudr_search_icon),
          contentDescription = "Search",
          tint = Color.Black,
          modifier = Modifier
            .size(20.dp)
        )
      }
    }
  )
}

@Preview
@Composable
fun FeedTopBarPreview() {
  //FeedTopBar()
}