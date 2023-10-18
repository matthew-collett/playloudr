import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.painterResource;
import com.playloudr.app.R

@Composable
fun FeedHeader(
//    onAddClick: () -> Unit = {},
//    onChatClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Box(modifier = Modifier.fillMaxSize()) {

            // Placeholder for Logo - You can replace with your own Image logo
            Image(
                painter = painterResource(id = R.drawable.ic_playloudr_logo),
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.TopCenter)
                    .padding(top = 0.dp)  // Adjust this value as needed
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Action button for adding a new post
//            IconButton(onClick = onAddClick) {
//                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
//            }

            // Action button for direct messages or notifications
//            IconButton(onClick = onChatClick) {
//                Icon(imageVector = Icons.Default.Chat, contentDescription = "Chat")
//            }
        }
    }
}

@Preview
@Composable
fun FeedHeaderPreview() {
    FeedHeader()
}
