import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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
      .padding(8.dp),
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

    Icon(
      painter = painterResource(id = R.drawable.ic_playloudr_search_icon),
      contentDescription = "search bar",
      modifier = Modifier
        .size(18.dp)
        .clickable {  }
    )



    //Button(onClick = { /*TODO*/ }) {}


  }
}

@Preview
@Composable
fun FeedHeaderPreview() {
  FeedHeader()
}
