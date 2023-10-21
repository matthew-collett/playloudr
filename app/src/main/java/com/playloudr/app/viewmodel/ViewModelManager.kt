package com.playloudr.app.viewmodel

import com.playloudr.app.model.repository.PostRepository

object ViewModelManager {

  fun getFeedViewModel(): FeedViewModel {
    return FeedViewModel(PostRepository)
  }

}