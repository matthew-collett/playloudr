package com.playloudr.app.model.client.aws

import aws.sdk.kotlin.services.dynamodb.DynamoDbClient
import aws.sdk.kotlin.services.s3.S3Client
import aws.sdk.kotlin.services.secretsmanager.SecretsManagerClient
import aws.smithy.kotlin.runtime.auth.awscredentials.CredentialsProvider
import com.playloudr.app.model.client.config.ConfigProvider

object AwsClientManager {
  private val config = ConfigProvider.get()
  private val provider: CredentialsProvider = CognitoProvider(config = config)

  private val dynamoDbClient: DynamoDbClient by lazy {
    DynamoDbClient {
      region = config.aws.region
      credentialsProvider = provider
    }
  }

  private val s3Client: S3Client by lazy {
    S3Client {
      region = config.aws.region
      credentialsProvider = provider
    }
  }

  private val secretsManagerClient: SecretsManagerClient by lazy {
    SecretsManagerClient {
      region = config.aws.region
      credentialsProvider = provider
    }
  }

  fun getDynamoDb(): DynamoDbClient {
    return dynamoDbClient
  }

  fun getS3(): S3Client {
    return s3Client
  }
}
