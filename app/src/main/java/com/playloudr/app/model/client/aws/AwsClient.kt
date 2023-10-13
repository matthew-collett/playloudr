package com.playloudr.app.model.client.aws

import aws.smithy.kotlin.runtime.client.SdkClient

abstract class AwsClient<T : SdkClient> {
  abstract fun getClient(): T
  fun closeClient() {
    getClient().close()
  }
}