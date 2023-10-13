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
    override fun getClient(): DynamoDbClient {
      return DynamoDbClient {
        region = config.aws.region
        credentialsProvider = provider
      }
    }
  }

  object S3ServiceClient : AbstractAwsClient<S3Client>() {
    override fun getClient(): S3Client {
      return S3Client {
        region = config.aws.region
        credentialsProvider = provider
      }
    }
  }

  object KmsServiceClient : AbstractAwsClient<KmsClient>() {
    override fun getClient(): KmsClient {
      return KmsClient {
        region = config.aws.region
        credentialsProvider = provider
      }
    }
  }
}