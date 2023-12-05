package com.playloudr.app.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playloudr.app.model.entity.UserEntity
import com.playloudr.app.model.repository.UserRepository
import com.playloudr.app.view.screens.search.SearchState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
  private val userRepository =  UserRepository
  private val _searchState = MutableStateFlow<SearchState>(SearchState.Idle)
  val searchState: StateFlow<SearchState> = _searchState
  var hasSearched = mutableStateOf(false)

  fun search(query: String) {
    if (query.isNotEmpty()) {
      viewModelScope.launch {
        try {
          val results = userRepository.searchUsers(query)
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
    hasSearched.value = true
  }

  fun clearSearch() {
    _searchState.value = SearchState.Idle
    hasSearched.value = false
  }
}