package com.playloudr.app.view

sealed class ViewState<out T> {
  object Loading : ViewState<Nothing>()
  data class Success<out R>(val data: R) : ViewState<R>()
  data class Error(val exception: Throwable) : ViewState<Nothing>()

  val isLoading get() = this is Loading
  val isSuccess get() = this is Success
  val isError get() = this is Error
}
