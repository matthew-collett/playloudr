package com.playloudr.app.model.entities

import com.playloudr.app.model.entities.PostEntity

val posts = listOf<PostEntity>(
  PostEntity("User1",
    "12345678",
    "A Song Title",
    "An Artist",
    "I love this song",
    "https://picsum.photos/id/237/200/300",
    "http://example.com/image1.png",
    Type.SONG
  ),
  PostEntity("User2",
    "12345678",
    "A Song Title",
    "An Artist",
    "I love this song",
    "http://example.com/image1.png",
    "http://example.com/image1.png",
    Type.SONG
  ),
  PostEntity("User3",
    "12345678",
    "A Song Title",
    "An Artist",
    "I love this song",
    "http://example.com/image1.png",
    "http://example.com/image1.png",
    Type.SONG
  )
)
