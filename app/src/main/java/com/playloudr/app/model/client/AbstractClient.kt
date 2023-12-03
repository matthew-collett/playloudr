package com.playloudr.app.model.client

import aws.smithy.kotlin.runtime.client.SdkClient

abstract class AbstractClient<T> {
  private val client: T by lazy { createClient() }

  protected abstract fun createClient(): T

  fun fetchClient(): T = client

  abstract fun closeClient()
}
