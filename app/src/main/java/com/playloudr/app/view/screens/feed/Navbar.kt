import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.playloudr.app.R

@Composable
fun Navbar(
//    onAddClick: () -> Unit = {},
//    onChatClick: () -> Unit = {}
  //modifier: Modifier
) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .height(56.dp)
      .padding(16.dp)
      .background(Color.White),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically,

  ) {



    Icon(
      painter = painterResource(id = R.drawable.ic_playloudr_house_door_outline), // Here we use the predefined material icon for search
      contentDescription = "Search", // accessibility description
      modifier = Modifier
        .size(24.dp)

    )

    Icon(
      painter = painterResource(id = R.drawable.ic_playloudr_plus_outline), // Here we use the predefined material icon for search
      contentDescription = "Search", // accessibility description
      modifier = Modifier
        .size(24.dp)
    )

    Icon(
      painter = painterResource(id = R.drawable.ic_playloudr_user_outline), // Here we use the predefined material icon for search
      contentDescription = "View Account Nav",
      modifier = Modifier
        .size(30.dp)
    )





  }
}

@Preview
@Composable
fun NavbarPreview() {
  Navbar()
}
