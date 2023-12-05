package com.playloudr.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playloudr.app.model.client.spotify.SpotifyTrack
import com.playloudr.app.model.dao.SpotifyDao
import com.playloudr.app.model.entity.PostEntity
import com.playloudr.app.model.repository.PostRepository
import com.playloudr.app.service.SessionManager
import com.playloudr.app.view.screens.create.CreatePostScreenState
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import java.time.Instant

@OptIn(FlowPreview::class)
class CreatePostViewModel : ViewModel() {
  private val postRepository = PostRepository
  private val spotifyDao = SpotifyDao
  private val _createPostState = MutableStateFlow<CreatePostScreenState>(CreatePostScreenState.Idle)
  val createPostState: StateFlow<CreatePostScreenState> = _createPostState
  val searchQuery = MutableStateFlow("")
  val isSongSelected = MutableStateFlow(false)

  init {
    viewModelScope.launch {
      searchQuery.debounce(300).filter { it.isNotEmpty() && !isSongSelected.value }
        .collect { query ->
          searchSongs(query)
        }
    }
  }

  fun createPost(song: SpotifyTrack, caption: String?) {
    viewModelScope.launch {
      val artists = song.artists.filter { it.name.isNotBlank() }.joinToString(", ") { it.name }
      val post = PostEntity(SessionManager.getCurrentUser()!!, Instant.now(), song.name, artists, caption ?: "", song.album.images[0].url)
      postRepository.createUserPost(post)
    }
  }

  private fun searchSongs(query: String) {
    viewModelScope.launch {
      _createPostState.value = CreatePostScreenState.Loading
      try {
        val results = spotifyDao.query(query, "track")
        _createPostState.value = CreatePostScreenState.Success(results, null)
      } catch (e: Exception) {
        _createPostState.value = CreatePostScreenState.Error(e)
      }
    }
  }

  fun onSongSelected(song: SpotifyTrack) {
    isSongSelected.value = true
    _createPostState.value = CreatePostScreenState.Success(emptyList(), song)
    searchQuery.value = song.name

  }
}
