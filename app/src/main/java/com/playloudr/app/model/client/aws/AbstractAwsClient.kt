package com.playloudr.app.model.client.aws

import aws.smithy.kotlin.runtime.client.SdkClient

abstract class AbstractAwsClient<T : SdkClient> {
  protected abstract fun createClient(): T

  fun getClient(): T = createClient()

  fun closeClient() {
    getClient().close()
  }
}