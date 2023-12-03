package com.playloudr.app.model.dao

import aws.sdk.kotlin.services.dynamodb.DynamoDbClient
import aws.sdk.kotlin.services.kms.KmsClient
import aws.sdk.kotlin.services.s3.S3Client
import aws.sdk.kotlin.services.secretsmanager.SecretsManagerClient
import aws.smithy.kotlin.runtime.client.SdkClient
import com.playloudr.app.model.client.aws.AwsClientManager
import com.playloudr.app.model.client.spotify.SpotifyServiceClient
import kotlin.reflect.KClass

abstract class AbstractDao<T : Any>(private val clientClass: KClass<T>) {
  private val manager: AwsClientManager by lazy { AwsClientManager }
  private val spotifyClient: SpotifyServiceClient by lazy { SpotifyServiceClient }

  @Suppress("UNCHECKED_CAST")
  protected fun getClient(): T {
    val client = when (clientClass) {
      DynamoDbClient::class -> manager.getDynamoDbClient().fetchClient() as T
      S3Client::class -> manager.getS3Client().fetchClient() as T
      KmsClient::class -> manager.getKmsClient().fetchClient() as T
      SecretsManagerClient::class -> manager.getSecretsManagerClient().fetchClient() as T
      SpotifyServiceClient::class -> spotifyClient.fetchClient() as T
      else -> throw IllegalArgumentException("Unsupported client class: ${clientClass.simpleName}")
    }
    return client
  }
}