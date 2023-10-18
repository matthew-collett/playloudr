package com.playloudr.app.model.dao

import aws.smithy.kotlin.runtime.client.SdkClient
import com.playloudr.app.model.client.aws.AwsClientManager

abstract class AbstractDao<T : SdkClient> {
  protected fun getClient(): SdkClient {
    return AwsClientManager.getClient()
  }
}