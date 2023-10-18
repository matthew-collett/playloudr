package com.playloudr.app.util

sealed class Response<out R> {
  data class Success<out T>(val data: T) : Response<T>()
  data class Failure(val exception: Throwable) : Response<Nothing>()

  val isSuccess: Boolean get() = this is Success
  val isFailure: Boolean get() = this is Failure

  fun getOrNull(): R? {
    return when (this) {
      is Success -> data
      else -> null
    }
  }

  companion object {
    fun <T> success(data: T): Response<T> = Success(data)
    fun failure(ex: Throwable): Response<Nothing> = Failure(ex)
  }
}