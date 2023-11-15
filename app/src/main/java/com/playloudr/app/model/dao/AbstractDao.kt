package com.playloudr.app.model.dao

import android.util.Log
import aws.sdk.kotlin.services.dynamodb.DynamoDbClient
import aws.sdk.kotlin.services.kms.KmsClient
import aws.sdk.kotlin.services.s3.S3Client
import aws.sdk.kotlin.services.secretsmanager.SecretsManagerClient
import aws.smithy.kotlin.runtime.client.SdkClient
import com.playloudr.app.model.client.aws.AwsClientManager
import com.playloudr.app.model.client.aws.AwsServiceClient.SecretServiceClient
import kotlin.reflect.KClass

abstract class AbstractDao<T : SdkClient>(private val clientClass: KClass<T>) {
  private val manager: AwsClientManager by lazy { AwsClientManager }

  @Suppress("UNCHECKED_CAST")
  protected fun getClient(): T {
    val client = when (clientClass) {
      DynamoDbClient::class -> manager.getDynamoDbClient().getClient()
      S3Client::class -> manager.getS3Client().getClient()
      KmsClient::class -> manager.getKmsClient().getClient()
      SecretsManagerClient::class -> manager.getSecretsManagerClient().getClient()
      else -> throw IllegalArgumentException("Unsupported client class: ${clientClass.simpleName}")
    }
    Log.d("AbstractDaoError", "Requested client class: ${clientClass.simpleName}")
    Log.d("AbstractDaoError", "Returned client class: ${client.javaClass.simpleName}")
    return client as T
  }
}