package com.playloudr.app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.playloudr.app.model.entities.PostEntity
import com.playloudr.app.model.entities.Type

class FeedViewModel : ViewModel() {

    // This could also be a Flow, depending on how you're loading data
    private val _posts = MutableLiveData<List<PostEntity>>()
    val posts: LiveData<List<PostEntity>> = _posts

    init {
        // For the sake of example, we're adding static data
        // In a real app, this is where you'd make a network request, etc.
        _posts.value = listOf(
            PostEntity("User1",
                "12345678",
                "A Song Title",
                "An Artist",
                "I love this song",
                "http://example.com/image1.png",
                "http://example.com/image1.png",
                Type.SONG
            )
            // Add more posts
        )
    }

    // Here, you would have other functions that might affect _posts,
    // like functions to refresh the data, handle pagination, etc.
}
