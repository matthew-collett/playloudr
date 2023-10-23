package com.playloudr.app.model.entities

import com.playloudr.app.model.enums.PostType
import java.time.Instant
import java.time.format.DateTimeFormatter

val posts = listOf<PostEntity>(
  PostEntity(
    "User1",
    Instant.now(),
    "Food Please",
    "Cutie Patooty",
    "I love this song",
    "https://picsum.photos/id/237/200/300",
    "http://example.com/image1.png",
    PostType.SINGLE,
    "https://fastly.picsum.photos/id/169/2500/1662.jpg?hmac=3DBeyQbiPxO88hBdhIuFPbvy2ff7cm9vmnq8lPIL9Ug"
  ),
  PostEntity(
    "User2",
    Instant.now(),
    "2 Dawgz Eating Dirt",
    "The 2 Dawgz",
    "Jammin",
    "https://fastly.picsum.photos/id/169/2500/1662.jpg?hmac=3DBeyQbiPxO88hBdhIuFPbvy2ff7cm9vmnq8lPIL9Ug",
    "http://example.com/image1.png",
    PostType.SINGLE,
    "https://fastly.picsum.photos/id/169/2500/1662.jpg?hmac=3DBeyQbiPxO88hBdhIuFPbvy2ff7cm9vmnq8lPIL9Ug"
  ),
  PostEntity(
    "User3",
    Instant.now(),
    "Moo moos a lot",
    "Moo Moo Da Cow",
    "This song puts me in a great \"moooood\"",
    "https://fastly.picsum.photos/id/200/1920/1280.jpg?hmac=-eKjMC8-UrbLMpy1A4OWrK0feVPB3Ka5KNOGibQzpRU",
    "http://example.com/image1.png",
    PostType.SINGLE,
    "https://fastly.picsum.photos/id/169/2500/1662.jpg?hmac=3DBeyQbiPxO88hBdhIuFPbvy2ff7cm9vmnq8lPIL9Ug"
  )
)
