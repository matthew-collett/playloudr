package com.playloudr.app.model.client.aws

import com.playloudr.app.model.client.aws.AwsServiceClient.DynamoDbServiceClient
import com.playloudr.app.model.client.aws.AwsServiceClient.KmsServiceClient
import com.playloudr.app.model.client.aws.AwsServiceClient.S3ServiceClient
import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KClass

class AwsClientManager {
  val clientMap = ConcurrentHashMap<KClass<*>, AwsClient<*>>()

  inline fun <reified T : AwsClient<*>> getServiceClient(): T? {
    return clientMap[T::class] as? T
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