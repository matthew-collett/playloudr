package com.playloudr.app.model.client.aws

import aws.sdk.kotlin.services.dynamodb.DynamoDbClient
import aws.sdk.kotlin.services.kms.KmsClient
import aws.sdk.kotlin.services.s3.S3Client
import aws.smithy.kotlin.runtime.client.SdkClient
import com.playloudr.app.model.client.config.ClientConfig
import com.playloudr.app.model.client.config.ConfigProvider

sealed class AwsServiceClient<T : SdkClient> : AbstractAwsClient<T>() {
  companion object {
    val config: ClientConfig by lazy { ConfigProvider.getReceiver() }
    val provider: CognitoProvider by lazy { CognitoProvider(config) }
  }

  object DynamoDbServiceClient : AwsServiceClient<DynamoDbClient>() {
    private val client: DynamoDbClient by lazy {
      DynamoDbClient {
        region = config.aws.region
        credentialsProvider = provider
      }
    }

    override fun createClient(): DynamoDbClient = client
  }

  object S3ServiceClient : AwsServiceClient<S3Client>() {
    private val client: S3Client by lazy {
      S3Client {
        region = config.aws.region
        credentialsProvider = provider
      }
    }

    override fun createClient(): S3Client = client
  }

  object KmsServiceClient : AwsServiceClient<KmsClient>() {
    private val client: KmsClient by lazy {
      KmsClient {
        region = config.aws.region
        credentialsProvider = provider
      }
    }

    override fun createClient(): KmsClient = client
  }
}
