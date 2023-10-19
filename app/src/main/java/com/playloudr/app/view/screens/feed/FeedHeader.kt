import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.sharp.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.playloudr.app.R

@Composable
fun FeedHeader(
//    onAddClick: () -> Unit = {},
//    onChatClick: () -> Unit = {}
) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .height(56.dp)
      .padding(16.dp),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
  ) {


    // Placeholder for Logo - You can replace with your own Image logo
    Image(
      painter = painterResource(id = R.drawable.ic_playloudr_logo),
      contentDescription = "App Logo",
      modifier = Modifier
        .size(120.dp),
      // .padding(start = 10.dp),
      contentScale = ContentScale.Crop
    )

//        Icon(
//          Icons.Default.Search,
//          contentDescription = "Search Bar",
//          modifier = Modifier
//            .padding(end = 16.dp)
//            .size(30.dp),
//            //.clickable()
//
//
//        )

    Icon(
      imageVector = Icons.Sharp.Search, // Here we use the predefined material icon for search
      contentDescription = "Search" // accessibility description
    )

//    Image(
//      painter = painterResource(id = R.drawable.ic_playloudr_search_icon), // Replace with your actual search icon resource
//      contentDescription = "Search",
//      modifier = Modifier
//        .size(30.dp), // Adjust the size as needed
//      contentScale = ContentScale.Crop
//
//    )
    //Button(onClick = { /*TODO*/ }) {}


  }
}

@Preview
@Composable
fun FeedHeaderPreview() {
  FeedHeader()
}
