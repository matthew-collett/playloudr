package com.playloudr.app.model.dao

import aws.sdk.kotlin.services.dynamodb.DynamoDbClient
import aws.sdk.kotlin.services.kms.KmsClient
import aws.sdk.kotlin.services.s3.S3Client
import aws.smithy.kotlin.runtime.client.SdkClient
import com.playloudr.app.model.client.aws.AwsClientManager
import com.playloudr.app.model.client.aws.AwsServiceClient.SecretServiceClient
import kotlin.reflect.KClass

abstract class AbstractDao<T : SdkClient>(private val clientClass: KClass<T>) {
  private val manager: AwsClientManager by lazy { AwsClientManager }

  @Suppress("UNCHECKED_CAST")
  protected fun getClient(): T {
    return when (clientClass) {
      DynamoDbClient::class -> manager.getDynamoDbClient().getClient()
      S3Client::class -> manager.getS3Client().getClient()
      KmsClient::class -> manager.getKmsClient().getClient()
      SecretServiceClient::class -> manager.getSecretsManagerClient().getClient()
      else -> throw IllegalArgumentException("Unsupported client class: ${clientClass.simpleName}")
    } as T
  }
}