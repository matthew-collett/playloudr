package com.playloudr.app.model.client.aws

import aws.sdk.kotlin.services.dynamodb.DynamoDbClient
import aws.sdk.kotlin.services.kms.KmsClient
import aws.sdk.kotlin.services.s3.S3Client
import com.playloudr.app.model.client.config.ClientConfig
import com.playloudr.app.model.client.config.ConfigProvider

sealed class AwsServiceClient {
  companion object {
    private val config: ClientConfig by lazy { ConfigProvider.getReceiver() }
    private val provider: CognitoProvider by lazy { CognitoProvider(config) }
  }

  object DynamoDbServiceClient: AbstractAwsClient<DynamoDbClient>() {
    private val client: DynamoDbClient by lazy {
      DynamoDbClient {
        region = config.aws.region
        credentialsProvider = provider
      }
    }
    override fun getClient(): DynamoDbClient {
      return client
    }
  }

  object S3ServiceClient: AbstractAwsClient<S3Client>() {
    private val client: S3Client by lazy {
      S3Client {
        region = config.aws.region
        credentialsProvider = provider
      }
    }
    override fun getClient(): S3Client {
      return client
    }
  }

  object KmsServiceClient: AbstractAwsClient<KmsClient>() {
    private val client: KmsClient by lazy {
      KmsClient {
        region = config.aws.region
        credentialsProvider = provider
      }
    }
    override fun getClient(): KmsClient {
      return client
    }
  }
}