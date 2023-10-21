package com.playloudr.app.util

import android.content.Context

abstract class Provider<T : Any> {
  private lateinit var receiver: T

  fun init(ctx: Context) {
    receiver = create(ctx = ctx)
  }

  fun getReceiver(): T {
    if (!::receiver.isInitialized) {
      throw IllegalStateException("${receiver::class.java} Provider not initialized!")
    }
    return receiver
  }

  abstract fun create(ctx: Context): T
}