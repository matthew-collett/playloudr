package com.playloudr.app.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playloudr.app.model.entity.UserEntity
import com.playloudr.app.model.repository.UserRepository
import com.playloudr.app.view.screens.search.SearchState
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import java.util.Locale

@OptIn(FlowPreview::class)
class SearchViewModel : ViewModel() {
  private val userRepository =  UserRepository
  private val _searchState = MutableStateFlow<SearchState>(SearchState.Idle)
  val searchState: StateFlow<SearchState> = _searchState
  val searchQuery = MutableStateFlow("")

  init {
    viewModelScope.launch {
      searchQuery.debounce(300)
        .collect { query ->
          search(query)
        }
    }
  }

  private fun search(query: String) {
    if (searchQuery.value.isEmpty()) {
      _searchState.value = SearchState.Idle
      return
    }
    viewModelScope.launch {
      _searchState.value = SearchState.Loading
      try {
        val results = userRepository.searchUsers(query.lowercase())
        if (results.isEmpty()) {
          _searchState.value = SearchState.NoResults("No users found")
        } else {
          _searchState.value = SearchState.Loaded(results)
        }
      } catch (e: Exception) {
        _searchState.value = SearchState.Error(e)
      }
    }
  }
}