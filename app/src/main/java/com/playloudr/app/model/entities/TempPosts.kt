package com.playloudr.app.model.entities

import com.playloudr.app.model.enums.PostType
import java.time.Instant

val posts = listOf(
  PostEntity(
    username = "Reecher",
    timestamp = Instant.now(),
    title = "Shape of You",
    artist = "Ed Sheeran",
    caption = "Loving this track! 🎵",
    imageUrl = "https://upload.wikimedia.org/wikipedia/en/b/b4/Shape_Of_You_%28Official_Single_Cover%29_by_Ed_Sheeran.png",
    audioUrl = "https://example.com/audio1.mp3",
    postType = PostType.SINGLE,
    profilePictureUrl = "https://example.com/profile1.jpg",
    postId = "1"
  ),
  PostEntity(
    username = "bobby_tunes",
    timestamp = Instant.now().minusSeconds(3600),
    title = "21",
    artist = "Adele",
    caption = "Such an iconic album.",
    imageUrl = "https://upload.wikimedia.org/wikipedia/en/1/1b/Adele_-_21.png",
    audioUrl = "https://example.com/audio2.mp3",
    postType = PostType.ALBUM,
    profilePictureUrl = "https://example.com/profile2.jpg",
    postId = "2"
  ),
  PostEntity(
    username = "charlie_vibes",
    timestamp = Instant.now().minusSeconds(200000),
    title = "Lost & Found",
    artist = "Jorja Smith",
    caption = "This EP is fire! 🔥",
    imageUrl = "https://upload.wikimedia.org/wikipedia/en/b/ba/Jorja_Smith_-_Lost_%26_Found.png",
    audioUrl = "https://example.com/audio3.mp3",
    postType = PostType.EP,
    profilePictureUrl = "https://example.com/profile3.jpg",
    postId = "3"
  ),
  PostEntity(
    username = "Reecher",
    timestamp = Instant.now().minusSeconds(172800),
    title = "Happy",
    artist = "Pharrell Williams",
    caption = "Weekend vibes 🕺",
    imageUrl = "https://upload.wikimedia.org/wikipedia/en/2/23/Pharrell_Williams_-_Happy.jpg",
    audioUrl = "https://example.com/audio4.mp3",
    postType = PostType.SINGLE,
    profilePictureUrl = "https://example.com/profile4.jpg",
    postId = "4"
  ),
  PostEntity(
    username = "Reecher",
    timestamp = Instant.now().minusSeconds(86400),
    title = "evermore",
    artist = "Taylor Swift",
    caption = "I love TSwift!",
    imageUrl = "https://upload.wikimedia.org/wikipedia/en/0/0a/Taylor_Swift_-_Evermore.png",
    audioUrl = "https://example.com/audio5.mp3",
    postType = PostType.ALBUM,
    profilePictureUrl = "https://example.com/profile5.jpg",
    postId = "5"
  ),
  PostEntity(
    username = "frank_grooves",
    timestamp = Instant.now().minusSeconds(864000),
    title = "Best of 2022",
    artist = "Various Artists",
    caption = "All my favorite tracks in one playlist.",
    imageUrl = "https://daily.jstor.org/wp-content/uploads/2023/01/good_times_with_bad_music_1050x700.jpg",
    audioUrl = "https://example.com/audio6.mp3",
    postType = PostType.PLAYLIST,
    profilePictureUrl = "https://example.com/profile6.jpg",
    postId = "6"
  ),
  PostEntity(
    username = "gina_harmony",
    timestamp = Instant.now().minusSeconds(21600),
    title = "Golden Hour",
    artist = "Kacey Musgraves",
    caption = "Perfect for a Sunday morning.",
    imageUrl = "https://upload.wikimedia.org/wikipedia/en/6/65/Kacey_Musgraves_-_Golden_Hour.png",
    audioUrl = "https://example.com/audio7.mp3",
    postType = PostType.ALBUM,
    profilePictureUrl = "https://example.com/profile7.jpg",
    postId ="7"
  ),
  PostEntity(
    username = "harry_beatbox",
    timestamp = Instant.now().minusSeconds(25200),
    title = "Wake Me Up",
    artist = "Avicii",
    caption = "Can't believe it's been years since this hit.",
    imageUrl = "https://upload.wikimedia.org/wikipedia/en/d/da/Avicii_Wake_Me_Up_Official_Single_Cover.png",
    audioUrl = "https://example.com/audio8.mp3",
    postType = PostType.SINGLE,
    profilePictureUrl = "https://example.com/profile8.jpg",
    postId = "8"
  ),
  PostEntity(
    username = "isabelle_muse",
    timestamp = Instant.now().minusSeconds(259200),
    title = "The Beatles EP Collection",
    artist = "The Beatles",
    caption = "Swifties where you at? 🌟",
    imageUrl = "https://upload.wikimedia.org/wikipedia/en/6/69/Beatles_EP_Collection_box_set_cover.jpg",
    audioUrl = "https://example.com/audio9.mp3",
    postType = PostType.EP,
    profilePictureUrl = "https://example.com/profile9.jpg",
    postId = "9"
  ),
  PostEntity(
    username = "jack_vinyl",
    timestamp = Instant.now().minusSeconds(432000),
    title = "Penny Lane",
    artist = "The Beatles",
    caption = "Throwback to the legends!",
    imageUrl = "https://upload.wikimedia.org/wikipedia/en/6/69/Pennystrawps.jpg",
    audioUrl = "https://example.com/audio10.mp3",
    postType = PostType.SINGLE,
    profilePictureUrl = "https://example.com/profile10.jpg",
    postId = "10"
  ),
  PostEntity(
    "eric.cuenat",
    Instant.now().minusSeconds(32400),
    "2 Dawgz Eating Dirt",
    "The 2 Dawgz",
    "Jammin",
    "https://fastly.picsum.photos/id/169/2500/1662.jpg?hmac=3DBeyQbiPxO88hBdhIuFPbvy2ff7cm9vmnq8lPIL9Ug",
    "http://example.com/image1.png",
    PostType.SINGLE,
    "https://fastly.picsum.photos/id/169/2500/1662.jpg?hmac=3DBeyQbiPxO88hBdhIuFPbvy2ff7cm9vmnq8lPIL9Ug",
    postId = "11"
  )
).sortedByDescending { it.timestamp }

