package com.playloudr.app.view.screens.create

import com.playloudr.app.model.client.spotify.SpotifyTrack
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation

@OptIn(ExperimentalCoilApi::class, ExperimentalComposeUiApi::class)
@Composable
fun CreatePostSongItem(
  song: SpotifyTrack,
  onSelect: (SpotifyTrack) -> Unit
) {
  val keyboardController = LocalSoftwareKeyboardController.current
  Row(
    verticalAlignment = Alignment.CenterVertically,
    modifier = Modifier
      .fillMaxWidth()
      .padding(8.dp)
      .clickable {
        onSelect(song)
        keyboardController?.hide()
      }
  ) {
    Image(
      painter = rememberImagePainter(
        data = song.album.images[0].url,
        builder = {
          transformations(CircleCropTransformation())
        }
      ),
      contentDescription = "Album Cover",
      modifier = Modifier
        .size(48.dp)
        .clip(CircleShape)
        .border(1.dp, Color.Gray, CircleShape)
    )

    Spacer(Modifier.width(16.dp))

    Column {
      Text(
        text = song.name,
        fontWeight = FontWeight.Bold,
        overflow = TextOverflow.Ellipsis
      )

      Text(
        text = (song.artists.filter { it.name.isNotBlank() }.map { it.name }).joinToString(", "),
        color = Color.Gray,
        style = MaterialTheme.typography.body2,
        overflow = TextOverflow.Ellipsis
      )
    }
  }
}