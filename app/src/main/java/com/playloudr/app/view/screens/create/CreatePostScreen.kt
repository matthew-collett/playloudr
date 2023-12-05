package com.playloudr.app.view.screens.create

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import com.playloudr.app.R

@Composable
fun CreatePostScreen(
//  viewModel: CreatePostViewModel,
//  navController: NavController
) {
  var imageUri by remember { mutableStateOf<ImageBitmap?>(null) }
  var caption by remember { mutableStateOf(TextFieldValue()) }
  val context = LocalContext.current

  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(16.dp),
    verticalArrangement = Arrangement.spacedBy(10.dp)
  ) {
    // Placeholder for the image with click event to select an image
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .height(250.dp)
        .clickable { /* Launch image picker here */ },
      contentAlignment = Alignment.Center
    ) {
      if (imageUri != null) {
        // Display selected image
        Image(
          bitmap = imageUri!!,
          contentDescription = "Selected Image",
          modifier = Modifier.fillMaxSize()
        )
      } else {
        // Display placeholder for image
        Icon(
          painter = painterResource(id = R.drawable.ic_playloudr_logo), // Replace with camera icon resource
          contentDescription = "Placeholder Image",
          tint = Color.Gray,
          modifier = Modifier.size(60.dp)
        )
      }
    }

    // Text field for the caption
    TextField(
      value = caption,
      onValueChange = { caption = it },
      label = { Text("Enter a caption...") },
      maxLines = 4,
      keyboardOptions = KeyboardOptions.Default.copy(
        imeAction = ImeAction.Done
      ),
      keyboardActions = KeyboardActions(onDone = {
        // Handle the done action of the keyboard
      }),
      modifier = Modifier.fillMaxWidth()
    )

    // Button to create a post
    Button(
      onClick = {
        // Handle the post creation here
      },
      modifier = Modifier.align(Alignment.End)
    ) {
      Text("Post")
    }
  }
}

// Placeholder for the camera icon resource
//@Composable
//fun CameraIcon() {
//  Icon(
//    imageVector = Icons.Default.CameraAlt,
//    contentDescription = "Camera Icon"
//  )
//}

//@Composable
//@Preview
//fun CreatePostScreenPreview() {
//  CreatePostScreen()
//}
