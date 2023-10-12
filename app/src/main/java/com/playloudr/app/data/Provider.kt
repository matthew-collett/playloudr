package com.playloudr.app.data

import android.content.Context

abstract class Provider<T : Any> {
  private lateinit var receiver: T

  fun init(ctx: Context) {
    receiver = create(ctx = ctx)
  }

  fun get(): T {
    if (!::receiver.isInitialized) {
      throw IllegalStateException("${receiver::class.java} Provider not initialized!")
    }
    return receiver
  }

  abstract fun create(ctx: Context): T
}
