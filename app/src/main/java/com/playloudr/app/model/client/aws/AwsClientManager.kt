package com.playloudr.app.model.client.aws

import aws.smithy.kotlin.runtime.client.SdkClient
import com.playloudr.app.model.client.aws.AwsServiceClient.DynamoDbServiceClient
import com.playloudr.app.model.client.aws.AwsServiceClient.KmsServiceClient
import com.playloudr.app.model.client.aws.AwsServiceClient.S3ServiceClient
import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KClass

object AwsClientManager {
  val clientMap: ConcurrentHashMap<KClass<*>, AbstractAwsClient<out SdkClient>> = ConcurrentHashMap()

  inline fun <reified T : AbstractAwsClient<*>> getClient(): T {
    return clientMap.computeIfAbsent(T::class) { klass ->
      when (klass) {
        DynamoDbServiceClient::class -> DynamoDbServiceClient
        S3ServiceClient::class -> S3ServiceClient
        KmsServiceClient::class -> KmsServiceClient
        else -> throw IllegalArgumentException("Invalid client type: ${klass.simpleName}")
      }
    } as T
  }

  fun getDynamoDb(): DynamoDbServiceClient {
    return clientMap.computeIfAbsent(DynamoDbServiceClient::class) {
      DynamoDbServiceClient
    } as DynamoDbServiceClient
  }

  fun getS3(): S3ServiceClient {
    return clientMap.computeIfAbsent(S3ServiceClient::class) {
      S3ServiceClient
    } as S3ServiceClient
  }

  fun getKms(): KmsServiceClient {
    return clientMap.computeIfAbsent(KmsServiceClient::class) {
      KmsServiceClient
    } as KmsServiceClient
  }

  fun closeClients() {
    clientMap.values.forEach { client ->
      client.closeClient()
    }
  }
}