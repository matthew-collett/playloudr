package com.playloudr.app.data.client.aws

import aws.smithy.kotlin.runtime.client.SdkClient

abstract class AwsClient<C : SdkClient> {
  abstract fun getClient(): C
  fun closeClient() {
    getClient().close()
  }
}