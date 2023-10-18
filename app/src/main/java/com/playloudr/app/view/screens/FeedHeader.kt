package com.playloudr.app.view.screens
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.playloudr.app.Greeting
import com.playloudr.app.R
import com.playloudr.app.view.theme.PlayloudrTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleTopBar() {
  val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
  Scaffold(
    modifier = Modifier
      .fillMaxSize()
      .nestedScroll(scrollBehavior.nestedScrollConnection),
    topBar = {
      LargeTopAppBar(
        title = {
          Text(text = "My notes")
        },
        navigationIcon = {
          IconButton(onClick = { /*TODO*/ }) {
            Icon(
              imageVector = Icons.Default.Menu,
              contentDescription = "Go back"
            )
          }
        },
        actions = {
          IconButton(onClick = { /*TODO*/ }) {
            Icon(
              imageVector = Icons.Default.FavoriteBorder,
              contentDescription = "Mark as favorite"
            )
          }
          IconButton(onClick = { /*TODO*/ }) {
            Icon(
              imageVector = Icons.Default.Edit,
              contentDescription = "Edit notes"
            )
          }
        },
        scrollBehavior = scrollBehavior,
      )
    }
  ) { values ->
    LazyColumn(
      modifier = Modifier
        .fillMaxSize()
        .padding(values)
    ) {
      items(100) {
        Text(
          text = "Item$it",
          modifier = Modifier.padding(16.dp)
        )
      }
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar() {
  Box(
    modifier = Modifier
      .height(75.dp)
      .fillMaxSize(),
    contentAlignment = Alignment.TopStart
  ) {
    // Assuming you have a drawable resource named 'app_logo'
    val painter = painterResource(id = R.drawable.logo_10_49_40_pm_removebg_preview)

    Image(
      painter = painter,
      contentDescription = "App Logo",  // Remember to consider accessibility
      modifier = Modifier
        .size(125.dp)
        .fillMaxWidth(),  // You can adjust this fraction to resize the image within the Box
      contentScale = ContentScale.Crop
    )
  }
}

@Preview(showBackground = true)
@Composable
fun SimpleTopBarPreview() {
  MyTopBar()
}


